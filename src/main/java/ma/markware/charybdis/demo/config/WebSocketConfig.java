package ma.markware.charybdis.demo.config;

import ma.markware.charybdis.demo.websocket.ChatSocket;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.socket.config.annotation.EnableWebSocket;
import org.springframework.web.socket.config.annotation.WebSocketConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketHandlerRegistry;

@Configuration
@EnableWebSocket
public class WebSocketConfig implements WebSocketConfigurer {

  private final ChatSocket chatSocket;

  public WebSocketConfig(final ChatSocket chatSocket) {
    this.chatSocket = chatSocket;
  }

  @Override
  public void registerWebSocketHandlers(WebSocketHandlerRegistry registry) {
    registry.addHandler(chatSocket, "/chat/{userId}").setAllowedOrigins("*");;
  }
}
