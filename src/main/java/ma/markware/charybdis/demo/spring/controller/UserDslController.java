package ma.markware.charybdis.demo.spring.controller;

import java.util.List;
import java.util.Optional;
import ma.markware.charybdis.demo.spring.dto.AddressDto;
import ma.markware.charybdis.demo.spring.dto.CreateUserDto;
import ma.markware.charybdis.demo.spring.dto.LoginUserDto;
import ma.markware.charybdis.demo.spring.dto.UserDto;
import ma.markware.charybdis.demo.spring.service.UserDslService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

// For simplicity, I chose not to handle cases where dsl queries are not fulfilled
// So by default, each time a query is not applied, I return BAD REQUEST
@RestController
@RequestMapping("/user/dsl")
public class UserDslController {

  private final UserDslService userDslService;

  public UserDslController(final UserDslService userDslService) {
    this.userDslService = userDslService;
  }

  @PostMapping(path = "/register", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> register(@RequestBody CreateUserDto createUserDto, @RequestHeader("user-agent") String userAgent) {
    boolean success = userDslService.register(createUserDto, userAgent);
    if (success) {
      return ResponseEntity.status(HttpStatus.CREATED)
                           .build();
    }
    return ResponseEntity.badRequest()
                         .build();
  }

  @PostMapping(path = "/login", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<UserDto> login(@RequestBody LoginUserDto loginUserDto, @RequestHeader("user-agent") String userAgent) {
    Optional<UserDto> userDto = userDslService.login(loginUserDto, userAgent);
    return userDto.map(ResponseEntity::ok)
                  .orElseGet(() -> ResponseEntity.badRequest()
                                                 .build());
  }

  @GetMapping(value = "/names", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<String>> listNames() {
    return ResponseEntity.ok(userDslService.getUsernames());
  }

  @PatchMapping(value = "/promote-admin/{username}")
  public ResponseEntity<Object> promoteToAdmin(@PathVariable("username") String username) {
    boolean success = userDslService.promoteToAdmin(username);
    if (success) {
      return ResponseEntity.noContent()
                           .build();
    }
    return ResponseEntity.badRequest()
                         .build();
  }

  @PatchMapping(value = "/create-address/{username}", consumes = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Object> createAddress(@PathVariable("username") String username, @RequestBody AddressDto addressDto) {
    boolean success = userDslService.addAddressToUser(username, addressDto);
    if (success) {
      return ResponseEntity.noContent()
                           .build();
    }
    return ResponseEntity.badRequest()
                         .build();
  }

  @DeleteMapping(value = "/delete-address/{username}/{index}")
  public ResponseEntity<Object> deleteAddress(@PathVariable("username") String username, @PathVariable("index") int index) {
    boolean success = userDslService.deleteAddress(username, index);
    if (success) {
      return ResponseEntity.noContent()
                           .build();
    }
    return ResponseEntity.badRequest()
                         .build();
  }

  @DeleteMapping(value = "/delete-addresses/{username}")
  public ResponseEntity<Object> deleteAddresses(@PathVariable("username") String username) {
    boolean success = userDslService.deleteAllAddresses(username);
    if (success) {
      return ResponseEntity.noContent()
                           .build();
    }
    return ResponseEntity.badRequest()
                         .build();
  }
}
