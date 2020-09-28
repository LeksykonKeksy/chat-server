package pl.leksy.krzysztof.chat.server.room.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.leksy.krzysztof.chat.server.room.exceptions.InvalidRoomPasswordException;
import pl.leksy.krzysztof.chat.server.room.exceptions.RoomFullException;
import pl.leksy.krzysztof.chat.server.room.repository.RoomRepository;
import pl.leksy.krzysztof.chat.server.web.dto.CreateRoomRequestDto;
import pl.leksy.krzysztof.chat.server.web.dto.JoinRoomRequestDto;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomService implements RoomFacade {
    private final RoomRepository roomRepository;

    @Override
    public String createRoom(CreateRoomRequestDto dto) {
        // TODO
        return null;
    }

    @Override
    public void joinRoom(JoinRoomRequestDto dto) throws RoomFullException, InvalidRoomPasswordException {
        // TODO
    }
}
