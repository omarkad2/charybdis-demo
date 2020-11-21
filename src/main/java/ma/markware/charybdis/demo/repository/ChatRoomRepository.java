package ma.markware.charybdis.demo.repository;

import java.util.Optional;
import java.util.UUID;
import ma.markware.charybdis.CqlTemplate;
import ma.markware.charybdis.batch.Batch;
import ma.markware.charybdis.demo.domain.ChatRoom;
import ma.markware.charybdis.demo.domain.ChatRoomByCode;
import ma.markware.charybdis.demo.domain.ChatRoomByCode_Table;
import ma.markware.charybdis.demo.domain.ChatRoom_Table;
import org.springframework.stereotype.Repository;

@Repository
public class ChatRoomRepository {

  private final CqlTemplate cqlTemplate;

  public ChatRoomRepository(final CqlTemplate cqlTemplate) {
    this.cqlTemplate = cqlTemplate;
  }

  public void create(ChatRoom chatRoom) {
    Batch batch = cqlTemplate.batch().logged();
    cqlTemplate.crud(batch).create(ChatRoom_Table.chatroom, chatRoom);
    cqlTemplate.crud(batch).create(ChatRoomByCode_Table.chatroom_by_code, new ChatRoomByCode(chatRoom));
    batch.execute();
  }

  public Optional<ChatRoomByCode> findChatRoomByCode(int chatRoomCode) {
    return cqlTemplate.crud()
                      .findOptional(ChatRoomByCode_Table.chatroom_by_code, ChatRoomByCode_Table.chatRoomCode.eq(chatRoomCode));
  }

  public Optional<ChatRoom> findChatRoomById(UUID chatRoomId) {
    return cqlTemplate.crud()
                      .findOptional(ChatRoom_Table.chatroom, ChatRoom_Table.chatRoomId.eq(chatRoomId));
  }
}
