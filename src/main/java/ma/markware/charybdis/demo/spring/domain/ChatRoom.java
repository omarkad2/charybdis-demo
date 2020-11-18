package ma.markware.charybdis.demo.spring.domain;

import static ma.markware.charybdis.demo.spring.domain.Defaultkeyspace.DEFAULT_KEYSPACE;

import java.util.UUID;
import ma.markware.charybdis.model.annotation.Column;
import ma.markware.charybdis.model.annotation.PartitionKey;
import ma.markware.charybdis.model.annotation.Table;

@Table(keyspace = DEFAULT_KEYSPACE, name = "chatroom")
public class ChatRoom extends AbstractChatRoom {

  // Used by charybdis
  public ChatRoom() {
  }

  public ChatRoom(final AbstractChatRoom chatRoom) {
    super(chatRoom);
  }

  @Override
  @Column
  @PartitionKey
  public UUID getChatRoomId() {
    return chatRoomId;
  }

  @Override
  @Column
  public int getChatRoomCode() {
    return chatRoomCode;
  }
}
