package pl.leksy.krzysztof.chat.server.web.dto;

import lombok.Data;

@Data
public class RoomListElementDto {
    private String name;
    private int slots;
    private int currentUsers;
    private String createdBy;
}
