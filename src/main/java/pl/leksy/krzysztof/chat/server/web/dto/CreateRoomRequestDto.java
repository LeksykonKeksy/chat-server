package pl.leksy.krzysztof.chat.server.web.dto;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
public class CreateRoomRequestDto {

    @Size(min = 1, max = 32)
    private String name;

    @NotNull
    private Boolean passwordProtected;

    @Size(min = 1, max = 60)
    private String password;

    @NotNull
    @Max(64)
    private Integer slots;

    @NotNull
    @Size(min = 1, max = 255)
    private String nickname;
}
