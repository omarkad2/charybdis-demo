package ma.markware.charybdis.demo.spring.service;

import java.util.List;
import java.util.Optional;
import ma.markware.charybdis.demo.spring.domain.RoleEnum;
import ma.markware.charybdis.demo.spring.domain.User;
import ma.markware.charybdis.demo.spring.dto.AddressDto;
import ma.markware.charybdis.demo.spring.dto.CreateUserDto;
import ma.markware.charybdis.demo.spring.dto.LoginUserDto;
import ma.markware.charybdis.demo.spring.dto.UserDto;
import ma.markware.charybdis.demo.spring.mapper.AddressMapper;
import ma.markware.charybdis.demo.spring.mapper.UserMapper;
import ma.markware.charybdis.demo.spring.repository.UserDslRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDslService {

  private final UserDslRepository userDslRepository;
  private final UserMapper userMapper;
  private final AddressMapper addressMapper;

  public UserDslService(final UserDslRepository userDslRepository, final UserMapper userMapper, final AddressMapper addressMapper) {
    this.userDslRepository = userDslRepository;
    this.userMapper = userMapper;
    this.addressMapper = addressMapper;
  }

  public boolean register(CreateUserDto createUserDto, String userAgent) {
    return userDslRepository.createUser(createUserDto.getUsername(), userAgent);
  }

  public Optional<UserDto> login(LoginUserDto loginUserDto, String userAgent) {
    String username = loginUserDto.getUsername();
    Optional<User> userOpt = userDslRepository.findUserByUsername(username);
    return userOpt.map(user -> {
      userDslRepository.updateAccessLogs(username, userAgent);
      return userMapper.toDto(userOpt.get());
    });
  }

  public List<String> getUsernames() {
    return userDslRepository.fetchUsernames();
  }

  public boolean promoteToAdmin(String username) {
    return userDslRepository.updateRole(username, RoleEnum.ADMIN);
  }

  public boolean addAddressToUser(String username, AddressDto addressDto) {
    return userDslRepository.addAddress(username, addressMapper.toSource(addressDto));
  }

  public boolean deleteAddress(String username, int index) {
    return userDslRepository.deleteAddressAtIndex(username, index);
  }

  public boolean deleteAllAddresses(String username) {
    return userDslRepository.deleteAddresses(username);
  }
}
