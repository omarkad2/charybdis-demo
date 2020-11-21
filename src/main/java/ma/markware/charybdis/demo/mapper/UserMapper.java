package ma.markware.charybdis.demo.mapper;

import ma.markware.charybdis.demo.domain.User;
import ma.markware.charybdis.demo.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class UserMapper {

  public UserDto map(User user) {
    UserDto userDto = new UserDto();
    userDto.setUserId(user.getUserId());
    userDto.setUsername(user.getUsername());
    return userDto;
  }
}
