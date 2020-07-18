package ma.markware.charybdis.demo.spring.mapper;

import ma.markware.charybdis.demo.spring.domain.User;
import ma.markware.charybdis.demo.spring.dto.UserDto;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

  User toSource(UserDto userDto);

  UserDto toDto(User user);
}

