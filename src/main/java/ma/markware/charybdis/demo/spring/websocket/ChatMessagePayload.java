package ma.markware.charybdis.demo.spring.websocket;

import com.google.gson.Gson;
import java.time.Instant;
import java.util.UUID;

public class ChatMessagePayload implements Payload {

  private UUID messageId;
  private UUID chatRoomId;
  private UUID authorId;
  private String authorUsername;
  private String content;
  private Instant sendingDate;

  public ChatMessagePayload() {
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

  public void setChatRoomId(UUID chatRoomId) {
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

  public void setContent(String content) {
    this.content = content;
  }

  public Instant getSendingDate() {
    return sendingDate;
  }

  public void setSendingDate(final Instant sendingDate) {
    this.sendingDate = sendingDate;
  }

  @Override
  public String encode() {
    return new Gson().toJson(this, ChatMessagePayload.class);
  }
}
