package pl.leksy.krzysztof.chat.server.web;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import pl.leksy.krzysztof.chat.server.room.exceptions.InvalidRoomPasswordException;
import pl.leksy.krzysztof.chat.server.room.exceptions.RoomFullException;
import pl.leksy.krzysztof.chat.server.room.service.RoomFacade;
import pl.leksy.krzysztof.chat.server.web.dto.CreateRoomRequestDto;
import pl.leksy.krzysztof.chat.server.web.dto.CreateRoomResponseDto;
import pl.leksy.krzysztof.chat.server.web.dto.JoinRoomRequestDto;

@Slf4j
@RestController
@RequiredArgsConstructor
public class RoomController {
    private final RoomFacade roomFacade;

    @PostMapping("/create")
    public CreateRoomResponseDto createRoom(CreateRoomRequestDto dto) {
        LOGGER.info("Creating room with data: {}", dto);

        final var roomName = roomFacade.createRoom(dto);

        return new CreateRoomResponseDto()
                .setRoomName(roomName);
    }

    @PostMapping("/join")
    public HttpStatus joinToRoom(JoinRoomRequestDto dto) {
        LOGGER.info("Joining room with data: {}", dto);

        try {
            roomFacade.joinRoom(dto);
            return HttpStatus.OK;
        } catch (RoomFullException ex) {
            return HttpStatus.CONFLICT;
        } catch (InvalidRoomPasswordException ex) {
            return HttpStatus.UNAUTHORIZED;
        }
    }

}
