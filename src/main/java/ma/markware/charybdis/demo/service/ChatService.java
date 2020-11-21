package ma.markware.charybdis.demo.service;

import java.io.IOException;
import java.time.Instant;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;
import ma.markware.charybdis.demo.domain.Message;
import ma.markware.charybdis.demo.dto.UserDto;
import ma.markware.charybdis.demo.mapper.MessageMapper;
import ma.markware.charybdis.demo.repository.ChatRoomRepository;
import ma.markware.charybdis.demo.repository.MessageRepository;
import ma.markware.charybdis.demo.websocket.ChatMessagePayload;
import ma.markware.charybdis.demo.websocket.MessageEncoderDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.socket.WebSocketSession;

@Service
public class ChatService {

  private static final Logger log = LoggerFactory.getLogger(ChatService.class);

  private static final Map<UUID, WebSocketSession> SESSIONS = new ConcurrentHashMap<>();

  private final ChatRoomRepository chatRoomRepository;
  private final MessageRepository messageRepository;
  private final MessageMapper messageMapper;

  public ChatService(final ChatRoomRepository chatRoomRepository, final MessageMapper messageMapper, final MessageRepository messageRepository) {
    this.chatRoomRepository = chatRoomRepository;
    this.messageMapper = messageMapper;
    this.messageRepository = messageRepository;
  }

  public void addSession(UUID userId, WebSocketSession session) {
    if (session != null && session.isOpen()) {
      SESSIONS.put(userId, session);
    }
  }

  public void removeSession(UUID userId, WebSocketSession session) {
    SESSIONS.remove(userId);
  }

  public void broadcastConnectedUsers() {
    Set<UserDto> connectedUsers = SESSIONS.values().stream()
                                          .map(session -> (UserDto) session.getAttributes().get("user"))
                                          .collect(Collectors.toSet());
    log.info("Connected users : {}", connectedUsers.stream()
                                                   .map(UserDto::getUsername)
                                                   .collect(Collectors.joining(", ")));
    SESSIONS.values()
            .forEach(session -> {
              try {
                session.sendMessage(MessageEncoderDecoder.encodeOnlineUsersMessage(connectedUsers));
              } catch (IOException e) {
                log.error("Unable to send online users message", e);
              }
            });
  }

  public void relayMessage(UUID authorId, ChatMessagePayload chatMessage) {
    Message message = messageMapper.map(chatMessage);
    message.setSendingDate(Instant.now());
    // Get chatRoom
    chatRoomRepository.findChatRoomById(message.getChatRoomId())
                      .ifPresent(chatRoom -> {
                        chatRoom.getUserIds()
                                .stream()
                                .filter(userId -> !Objects.equals(authorId, userId))
                                .forEach(userId -> {
                                  WebSocketSession receiverSession = SESSIONS.get(userId);
                                  if (receiverSession != null && receiverSession.isOpen()) {
                                    try {
                                      receiverSession.sendMessage(MessageEncoderDecoder.encodeChatMessage(chatMessage));
                                    } catch (IOException e) {
                                      log.error("Unable to send chat message to {}", userId, e);
                                    }
                                  }
                                });
                        messageRepository.create(message);
                      });
  }
}
