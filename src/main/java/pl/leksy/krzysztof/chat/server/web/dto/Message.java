package pl.leksy.krzysztof.chat.server.web.dto;

import lombok.Data;

@Data
public class Message {
    private String from;
    private String text;
}
