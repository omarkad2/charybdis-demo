package ma.markware.charybdis.demo.spring.domain;

import java.util.Set;
import java.util.UUID;
import ma.markware.charybdis.model.annotation.Column;

public class AbstractChatRoom extends AuditableEntity {

  protected UUID chatRoomId;

  protected int chatRoomCode;

  @Column
  private UUID creatorId;

  @Column
  private Set<UUID> userIds;

  @Column
  private boolean single;

  public AbstractChatRoom() {
  }

  public AbstractChatRoom(final int chatRoomCode, final UUID creatorId, final Set<UUID> userIds, final boolean single) {
    this.chatRoomId = UUID.randomUUID();
    this.chatRoomCode = chatRoomCode;
    this.creatorId = creatorId;
    this.userIds = userIds;
    this.single = single;
  }

  public AbstractChatRoom(final AbstractChatRoom chatRoom) {
    this.chatRoomId = chatRoom.chatRoomId;
    this.chatRoomCode = chatRoom.chatRoomCode;
    this.creatorId = chatRoom.creatorId;
    this.userIds = chatRoom.userIds;
    this.single = chatRoom.single;
  }

  public UUID getChatRoomId() {
    return chatRoomId;
  }

  public void setChatRoomId(final UUID chatRoomId) {
    this.chatRoomId = chatRoomId;
  }

  public int getChatRoomCode() {
    return chatRoomCode;
  }

  public void setChatRoomCode(final int chatRoomCode) {
    this.chatRoomCode = chatRoomCode;
  }

  public UUID getCreatorId() {
    return creatorId;
  }

  public void setCreatorId(final UUID creatorId) {
    this.creatorId = creatorId;
  }

  public Set<UUID> getUserIds() {
    return userIds;
  }

  public void setUserIds(final Set<UUID> userIds) {
    this.userIds = userIds;
  }

  public boolean isSingle() {
    return single;
  }

  public void setSingle(final boolean single) {
    this.single = single;
  }
}
