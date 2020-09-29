package pl.leksy.krzysztof.chat.server.room.service;

import pl.leksy.krzysztof.chat.server.web.dto.CreateRoomRequestDto;
import pl.leksy.krzysztof.chat.server.web.dto.JoinRoomRequestDto;
import pl.leksy.krzysztof.chat.server.web.dto.RoomListDto;

public interface RoomFacade {
    String createRoom(CreateRoomRequestDto dto);

    void joinRoom(JoinRoomRequestDto dto);

    RoomListDto getPublicRooms();
}
