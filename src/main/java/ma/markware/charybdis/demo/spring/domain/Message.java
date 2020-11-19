package ma.markware.charybdis.demo.spring.domain;

import static ma.markware.charybdis.demo.spring.domain.Defaultkeyspace.DEFAULT_KEYSPACE;

import java.time.Instant;
import java.util.UUID;
import ma.markware.charybdis.model.annotation.ClusteringKey;
import ma.markware.charybdis.model.annotation.Column;
import ma.markware.charybdis.model.annotation.Table;

@Table(keyspace = DEFAULT_KEYSPACE, name = "message")
public class Message extends AuditableEntity {

  @Column
  @ClusteringKey
  private UUID messageId;

  @Column
  protected UUID chatRoomId;

  @Column
  private UUID authorId;

  @Column
  private String authorUsername;

  @Column
  private String content;

  @Column
  private Instant sendingDate;

  public Message() {
  }

  public Message(final Message message) {
    this.messageId = message.messageId;
    this.chatRoomId = message.chatRoomId;
    this.authorId = message.authorId;
    this.authorUsername = message.authorUsername;
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

  public String getAuthorUsername() {
    return authorUsername;
  }

  public void setAuthorUsername(final String authorUsername) {
    this.authorUsername = authorUsername;
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
