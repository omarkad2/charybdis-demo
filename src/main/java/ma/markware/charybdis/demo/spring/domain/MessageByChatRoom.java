package ma.markware.charybdis.demo.spring.domain;

import static ma.markware.charybdis.demo.spring.domain.Defaultkeyspace.DEFAULT_KEYSPACE;

import java.util.UUID;
import ma.markware.charybdis.model.annotation.Column;
import ma.markware.charybdis.model.annotation.PartitionKey;
import ma.markware.charybdis.model.annotation.Table;

@Table(keyspace = DEFAULT_KEYSPACE, name = "message_by_chatroom")
public class MessageByChatRoom extends AbstractMessage {

  public MessageByChatRoom() {
  }

  public MessageByChatRoom(final AbstractMessage message) {
    super(message);
  }

  @Override
  @Column
  public UUID getMessageId() {
    return messageId;
  }

  @Override
  @Column
  @PartitionKey
  public UUID getChatRoomId() {
    return chatRoomId;
  }
}
