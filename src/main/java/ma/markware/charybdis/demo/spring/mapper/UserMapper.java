package ma.markware.charybdis.demo.spring.mapper;

import ma.markware.charybdis.demo.spring.domain.User;
import ma.markware.charybdis.demo.spring.dto.UserDto;
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
