package ma.markware.charybdis.demo.domain;

import java.util.UUID;
import ma.markware.charybdis.model.annotation.Column;
import ma.markware.charybdis.model.annotation.PartitionKey;
import ma.markware.charybdis.model.annotation.Table;

@Table(keyspace = Defaultkeyspace.DEFAULT_KEYSPACE, name = "message_by_chatroom")
public class MessageByChatRoom extends Message {

  // Used by charybdis
  public MessageByChatRoom() {
  }

  public MessageByChatRoom(final Message message) {
    super(message);
  }

  @Override
  @Column
  @PartitionKey
  public UUID getChatRoomId() {
    return chatRoomId;
  }
}
