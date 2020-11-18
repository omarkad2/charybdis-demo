package ma.markware.charybdis.demo.spring.domain;

import static ma.markware.charybdis.demo.spring.domain.Defaultkeyspace.DEFAULT_KEYSPACE;

import java.util.UUID;
import ma.markware.charybdis.model.annotation.Column;
import ma.markware.charybdis.model.annotation.PartitionKey;
import ma.markware.charybdis.model.annotation.Table;

@Table(keyspace = DEFAULT_KEYSPACE, name = "chatroom_by_code")
public class ChatRoomByCode extends AbstractChatRoom {

  // Used by charybdis
  public ChatRoomByCode() {
  }

  public ChatRoomByCode(final AbstractChatRoom chatRoom) {
    super(chatRoom);
  }

  @Override
  @Column
  public UUID getChatRoomId() {
    return chatRoomId;
  }

  @Override
  @Column
  @PartitionKey
  public int getChatRoomCode() {
    return chatRoomCode;
  }
}
