package pl.leksy.krzysztof.chat.server.web.dto;

import lombok.Data;

@Data
public class CreateRoomRequestDto {
    private String name;
    private boolean passwordProtected;
    private String password;
    private int slots;
    private String nickname;
}
