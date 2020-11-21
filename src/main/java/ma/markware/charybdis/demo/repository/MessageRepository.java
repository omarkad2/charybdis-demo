package ma.markware.charybdis.demo.repository;

import java.util.UUID;
import ma.markware.charybdis.CqlTemplate;
import ma.markware.charybdis.batch.Batch;
import ma.markware.charybdis.demo.domain.Message;
import ma.markware.charybdis.demo.domain.MessageByChatRoom;
import ma.markware.charybdis.demo.domain.MessageByChatRoom_Table;
import ma.markware.charybdis.demo.domain.Message_Table;
import ma.markware.charybdis.query.PageRequest;
import ma.markware.charybdis.query.PageResult;
import org.springframework.stereotype.Repository;

@Repository
public class MessageRepository {

  private final CqlTemplate cqlTemplate;

  public MessageRepository(final CqlTemplate cqlTemplate) {
    this.cqlTemplate = cqlTemplate;
  }

  public void create(Message message) {
    Batch batch = cqlTemplate.batch().logged();
    cqlTemplate.crud(batch).create(Message_Table.message, message);
    cqlTemplate.crud(batch).create(MessageByChatRoom_Table.message_by_chatroom, new MessageByChatRoom(message));
    batch.execute();
  }

  public PageResult<MessageByChatRoom> getMessagesByChatRoomPaged(UUID chatRoomId, PageRequest pageRequest) {
    return cqlTemplate.crud()
                      .find(MessageByChatRoom_Table.message_by_chatroom, MessageByChatRoom_Table.chatRoomId.eq(chatRoomId), pageRequest);
  }
}
