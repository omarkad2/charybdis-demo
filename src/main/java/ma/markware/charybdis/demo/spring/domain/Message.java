package ma.markware.charybdis.demo.spring.domain;

import static ma.markware.charybdis.demo.spring.domain.Defaultkeyspace.DEFAULT_KEYSPACE;

import java.util.UUID;
import ma.markware.charybdis.model.annotation.Column;
import ma.markware.charybdis.model.annotation.PartitionKey;
import ma.markware.charybdis.model.annotation.Table;

@Table(keyspace = DEFAULT_KEYSPACE, name = "message")
public class Message extends AbstractMessage {

  public Message() {
  }

  public Message(final AbstractMessage message) {
    super(message);
  }

  @Override
  @Column
  @PartitionKey
  public UUID getMessageId() {
    return messageId;
  }

  @Override
  @Column
  public UUID getChatRoomId() {
    return chatRoomId;
  }
}
