package ma.markware.charybdis.demo.spring.websocket;

import java.net.URI;
import java.util.Objects;
import java.util.UUID;
import ma.markware.charybdis.demo.spring.dto.UserDto;
import ma.markware.charybdis.demo.spring.service.ChatService;
import ma.markware.charybdis.demo.spring.service.UserService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

@Component
public class ChatSocket extends TextWebSocketHandler {

  private static final Logger log = LoggerFactory.getLogger(ChatSocket.class);

  private final ChatService chatService;
  private final UserService userService;

  public ChatSocket(final ChatService chatService, final UserService userService) {
    this.chatService = chatService;
    this.userService = userService;
  }

  @Override
  public void handleTextMessage(WebSocketSession session, TextMessage message) {
    UserDto userDto = resolveUser(session);
    log.info("Received message {} from {}", message, userDto);
    if (StringUtils.isNotBlank(message.getPayload())) {
      WebsocketMessage websocketMessage = MessageEncoderDecoder.decodeTextMessage(message);
      Payload payload = websocketMessage.getPayload();
      if (payload instanceof ChatMessagePayload) {
        chatService.relayMessage(userDto.getUserId(), (ChatMessagePayload) payload);
      }
    }
  }

  @Override
  public void afterConnectionEstablished(WebSocketSession session) {
    UserDto userDto = resolveUser(session);
    log.info("Opened session for {}", userDto);
    chatService.addSession(userDto.getUserId(), session);
    chatService.broadcastConnectedUsers();
  }

  @Override
  public void afterConnectionClosed(WebSocketSession session, CloseStatus status) {
    UserDto userDto = resolveUser(session);
    log.info("Closed session for {}", userDto);
    chatService.removeSession(userDto.getUserId(), session);
    chatService.broadcastConnectedUsers();
  }

  private UserDto resolveUser(WebSocketSession session) {
    UserDto user = (UserDto) session.getAttributes().get("user");
    if (user == null) {
      UUID uuid = extractUserIdFromUri(Objects.requireNonNull(session.getUri()));
      user = userService.getUserById(uuid); // throws error if user not found
      session.getAttributes().put("user", user);
    }
    log.info("Resolved user: {}", user);
    return user;
  }

  @Override
  public void handleTransportError(WebSocketSession session, Throwable exception) {
    log.error("Transport error", exception);
  }

  private UUID extractUserIdFromUri(URI uri) {
    String path = uri.getPath();
    String uuidStr = path.substring(path.lastIndexOf('/') + 1);
    try{
      return UUID.fromString(uuidStr);
    } catch (IllegalArgumentException exception){
      throw new IllegalStateException(String.format("Invalid userId '%s'=> Ignore", uuidStr));
    }
  }
}
