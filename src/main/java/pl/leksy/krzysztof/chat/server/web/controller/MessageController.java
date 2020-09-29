package pl.leksy.krzysztof.chat.server.web.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import pl.leksy.krzysztof.chat.server.web.dto.Message;

@Slf4j
@Controller
public class MessageController {

    @MessageMapping("/chat")
    @SendTo("/topic/chat") // TODO: specific user
    public Message handleMessage(@Payload Message msg) {
        LOGGER.info("Received message: {}", msg);
        return msg;
    }

    // TODO: ogarnąć path
    @MessageMapping("/chat/chat")
    @SendTo("/topic/chat")
    public Message handleMessage2(@Payload Message msg) {
        LOGGER.info("Received message: {}", msg);
        return msg;
    }

}
