package pl.leksy.krzysztof.chat.server.web.dto;

import lombok.Data;

import java.util.List;

@Data
public class RoomListDto {
    private List<RoomListElementDto> rooms;
}
