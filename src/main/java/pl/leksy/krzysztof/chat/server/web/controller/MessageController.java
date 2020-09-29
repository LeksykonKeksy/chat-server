package pl.leksy.krzysztof.chat.server.web.controller;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;
import pl.leksy.krzysztof.chat.server.web.dto.Message;

@Controller
public class MessageController {

    @MessageMapping("/chat")
    @SendTo("/topic/chat") // TODO: specific user
    public Message handleMessage(Message msg) {
        return msg;
    }

}
