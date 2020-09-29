package pl.leksy.krzysztof.chat.server.web.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class JoinRoomRequestDto {
    @NotNull
    @Size(min = 1, max = 255)
    private String name;

    @Size(min = 1, max = 60)
    private String password;

    @NotNull
    @Size(min = 1, max = 255)
    private String nickname;
}
