package ma.markware.charybdis.demo.mapper;

import ma.markware.charybdis.demo.domain.Message;
import ma.markware.charybdis.demo.websocket.ChatMessagePayload;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

  public ChatMessagePayload map(Message message) {
    ChatMessagePayload messageDto = new ChatMessagePayload();
    messageDto.setMessageId(message.getMessageId());
    messageDto.setChatRoomId(message.getChatRoomId());
    messageDto.setAuthorUsername(message.getAuthorUsername());
    messageDto.setAuthorId(message.getAuthorId());
    messageDto.setContent(message.getContent());
    messageDto.setSendingDate(message.getSendingDate());
    return messageDto;
  }

  public Message map(ChatMessagePayload messageDto) {
    Message message = new Message();
    message.setMessageId(messageDto.getMessageId());
    message.setChatRoomId(messageDto.getChatRoomId());
    message.setAuthorId(messageDto.getAuthorId());
    message.setAuthorUsername(messageDto.getAuthorUsername());
    message.setContent(messageDto.getContent());
    message.setSendingDate(messageDto.getSendingDate());
    return message;
  }
}
