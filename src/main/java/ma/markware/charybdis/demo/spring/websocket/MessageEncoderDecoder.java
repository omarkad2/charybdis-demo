package ma.markware.charybdis.demo.spring.websocket;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.util.Set;
import ma.markware.charybdis.demo.spring.dto.UserDto;
import org.springframework.web.socket.TextMessage;

public class MessageEncoderDecoder {

  public static TextMessage encodeChatMessage(final ChatMessagePayload chatMessagePayload) {
    WebsocketMessage message = new WebsocketMessage();
    message.setEvent("chat-message");
    message.setPayload(chatMessagePayload);
    return new TextMessage(new Gson().toJson(message, WebsocketMessage.class));
  }

  public static TextMessage encodeOnlineUsersMessage(final Set<UserDto> onlineUsers) {
    WebsocketMessage message = new WebsocketMessage();
    message.setEvent("online-users");
    message.setPayload(new OnlineUsersPayload(onlineUsers));
    return new TextMessage(new Gson().toJson(message, WebsocketMessage.class));
  }

  public static WebsocketMessage decodeTextMessage(final TextMessage message) {
    WebsocketMessage websocketMessage = new WebsocketMessage();
    JsonObject obj = JsonParser.parseString(message.getPayload()).getAsJsonObject();
    String event = obj.get("event").getAsString();
    websocketMessage.setEvent(event);
    if ("chat-message".equals(event)) {
      Gson gson = new Gson();
      websocketMessage.setPayload(gson.fromJson(obj.get("payload")
                                                   .toString(), ChatMessagePayload.class));
    }
    return websocketMessage;
  }
}
