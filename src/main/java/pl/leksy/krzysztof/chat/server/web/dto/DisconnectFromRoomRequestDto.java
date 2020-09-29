package pl.leksy.krzysztof.chat.server.web.dto;

import lombok.Data;

@Data
public class DisconnectFromRoomRequestDto {
    private String roomName;
}
