package ma.markware.charybdis.demo.spring.domain;

import java.time.Instant;
import java.util.UUID;
import ma.markware.charybdis.model.annotation.Column;

public class AbstractMessage extends AuditableEntity {

  protected UUID messageId;

  protected UUID chatRoomId;

  @Column
  private UUID authorId;

  @Column
  private String content;

  @Column
  private Instant sendingDate;

  public AbstractMessage() {
  }

  public AbstractMessage(final AbstractMessage message) {
    this.messageId = message.messageId;
    this.chatRoomId = message.chatRoomId;
    this.authorId = message.authorId;
    this.content = message.content;
    this.sendingDate = message.sendingDate;
  }


  public UUID getMessageId() {
    return messageId;
  }

  public void setMessageId(final UUID messageId) {
    this.messageId = messageId;
  }

  public UUID getChatRoomId() {
    return chatRoomId;
  }

  public void setChatRoomId(final UUID chatRoomId) {
    this.chatRoomId = chatRoomId;
  }

  public UUID getAuthorId() {
    return authorId;
  }

  public void setAuthorId(final UUID authorId) {
    this.authorId = authorId;
  }

  public String getContent() {
    return content;
  }

  public void setContent(final String content) {
    this.content = content;
  }

  public Instant getSendingDate() {
    return sendingDate;
  }

  public void setSendingDate(final Instant sendingDate) {
    this.sendingDate = sendingDate;
  }
}
