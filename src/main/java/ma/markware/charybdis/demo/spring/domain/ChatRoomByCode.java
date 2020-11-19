package ma.markware.charybdis.demo.spring.domain;

import static ma.markware.charybdis.demo.spring.domain.Defaultkeyspace.DEFAULT_KEYSPACE;

import ma.markware.charybdis.model.annotation.Column;
import ma.markware.charybdis.model.annotation.PartitionKey;
import ma.markware.charybdis.model.annotation.Table;

@Table(keyspace = DEFAULT_KEYSPACE, name = "chatroom_by_code")
public class ChatRoomByCode extends ChatRoom {

  // Used by charybdis
  public ChatRoomByCode() {
  }

  public ChatRoomByCode(final ChatRoom chatRoom) {
    super(chatRoom);
  }

  @Override
  @Column
  @PartitionKey
  public int getChatRoomCode() {
    return chatRoomCode;
  }
}
