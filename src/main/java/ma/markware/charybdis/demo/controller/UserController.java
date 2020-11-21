package ma.markware.charybdis.demo.controller;

import ma.markware.charybdis.demo.dto.UserDto;
import ma.markware.charybdis.demo.service.UserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/user/v1")
public class UserController {

  private final UserService userService;

  public UserController(final UserService userService) {
    this.userService = userService;
  }

  @GetMapping("/{username}")
  public UserDto getChatRoomId(@PathVariable("username") String username) {
    return userService.resolveUser(username);
  }
}
