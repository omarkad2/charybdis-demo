package ma.markware.charybdis.demo.spring.service;

import java.util.UUID;
import ma.markware.charybdis.demo.spring.domain.User;
import ma.markware.charybdis.demo.spring.dto.UserDto;
import ma.markware.charybdis.demo.spring.mapper.UserMapper;
import ma.markware.charybdis.demo.spring.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  public UserService(final UserRepository userRepository, final UserMapper userMapper) {
    this.userRepository = userRepository;
    this.userMapper = userMapper;
  }

  public UserDto getUserById(UUID userId) {
    User user = userRepository.findByUserId(userId).orElseThrow(() -> new IllegalStateException(String.format("No user found with id '%s'", userId)));
    return userMapper.map(user);
  }

  public UserDto resolveUser(String username) {
    User user = userRepository.findByUsername(username)
                              .orElseGet(() -> userRepository.create(new User(username)));
    return userMapper.map(user);
  }
}
