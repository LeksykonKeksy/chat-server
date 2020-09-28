package pl.leksy.krzysztof.chat.server.web.dto;

import lombok.Data;

@Data
public class JoinRoomRequestDto {
    private String name;
    private String password;
    private String nickname;
}
