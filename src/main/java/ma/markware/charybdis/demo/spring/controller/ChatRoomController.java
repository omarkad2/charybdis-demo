package ma.markware.charybdis.demo.spring.controller;

import java.util.UUID;
import ma.markware.charybdis.demo.spring.dto.Page;
import ma.markware.charybdis.demo.spring.service.ChatRoomService;
import ma.markware.charybdis.demo.spring.websocket.ChatMessagePayload;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/chatroom/v1")
public class ChatRoomController {

  private final ChatRoomService chatRoomService;

  public ChatRoomController(final ChatRoomService chatRoomService) {
    this.chatRoomService = chatRoomService;
  }

  @GetMapping("/{senderId}/{receiverId}")
  public UUID getChatRoomId(@PathVariable("senderId") UUID senderId, @PathVariable("receiverId") UUID receiverId) {
    return chatRoomService.getChatRoomId(senderId, receiverId);
  }

  @GetMapping("/{chatRoomId}/messages")
  public Page<ChatMessagePayload> getMessages(@PathVariable("chatRoomId") UUID chatRoomId, @RequestParam(value = "limit", required = false) Integer limit,
      @RequestParam(value = "page", required = false) String pagingState) {
    return chatRoomService.getMessagesInChatRoom(chatRoomId, limit, pagingState);
  }
}
