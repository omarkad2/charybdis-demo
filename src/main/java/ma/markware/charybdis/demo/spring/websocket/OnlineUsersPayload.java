package ma.markware.charybdis.demo.spring.websocket;

import com.google.gson.Gson;
import java.util.Set;
import ma.markware.charybdis.demo.spring.dto.UserDto;

public class OnlineUsersPayload implements Payload {

  private Set<UserDto> users;

  public OnlineUsersPayload() {
  }

  public OnlineUsersPayload(Set<UserDto> users) {
    this.users = users;
  }

  public Set<UserDto> getUsers() {
    return users;
  }

  public void setUsers(final Set<UserDto> users) {
    this.users = users;
  }

  @Override
  public String encode() {
    return new Gson().toJson(this, OnlineUsersPayload.class);
  }
}
