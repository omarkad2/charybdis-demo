package ma.markware.charybdis.demo.spring.websocket;

import java.io.Serializable;

public class WebsocketMessage implements Serializable {

    private String event;
    private Payload payload;

    public String getEvent() {
      return event;
    }

    public void setEvent(final String event) {
      this.event = event;
    }

    public Payload getPayload() {
      return payload;
    }

    public void setPayload(final Payload payload) {
      this.payload = payload;
    }

    @Override
    public String toString() {
      return "WebsocketMessage{" + "event='" + event + '\'' + ", payload=" + payload + '}';
    }
}
