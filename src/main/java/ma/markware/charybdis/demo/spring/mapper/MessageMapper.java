package ma.markware.charybdis.demo.spring.mapper;

import ma.markware.charybdis.demo.spring.domain.AbstractMessage;
import ma.markware.charybdis.demo.spring.websocket.ChatMessagePayload;
import org.springframework.stereotype.Component;

@Component
public class MessageMapper {

  public ChatMessagePayload map(AbstractMessage message) {
    ChatMessagePayload messageDto = new ChatMessagePayload();
    messageDto.setMessageId(message.getMessageId());
    messageDto.setChatRoomId(message.getChatRoomId());
    messageDto.setAuthorId(message.getAuthorId());
    messageDto.setContent(message.getContent());
    messageDto.setSendingDate(message.getSendingDate());
    return messageDto;
  }

  public AbstractMessage map(ChatMessagePayload messageDto) {
    AbstractMessage message = new AbstractMessage();
    message.setMessageId(messageDto.getMessageId());
    message.setChatRoomId(messageDto.getChatRoomId());
    message.setAuthorId(messageDto.getAuthorId());
    message.setContent(messageDto.getContent());
    message.setSendingDate(messageDto.getSendingDate());
    return message;
  }
}
