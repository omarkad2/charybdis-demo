package ma.markware.charybdis.demo.spring.dto;

import java.util.UUID;

public class UserDto {

  private UUID userId;
  private String username;

  public UUID getUserId() {
    return userId;
  }

  public void setUserId(final UUID userId) {
    this.userId = userId;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(final String username) {
    this.username = username;
  }

  @Override
  public String toString() {
    return "UserDto{" + "userId=" + userId + ", username='" + username + '\'' + '}';
  }
}
