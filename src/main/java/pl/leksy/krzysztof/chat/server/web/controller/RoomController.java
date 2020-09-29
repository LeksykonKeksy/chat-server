package pl.leksy.krzysztof.chat.server.web.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import pl.leksy.krzysztof.chat.server.room.service.RoomFacade;
import pl.leksy.krzysztof.chat.server.web.dto.CreateRoomRequestDto;
import pl.leksy.krzysztof.chat.server.web.dto.CreateRoomResponseDto;
import pl.leksy.krzysztof.chat.server.web.dto.JoinRoomRequestDto;

import javax.validation.Valid;

@Slf4j
@Validated
@RestController
@RequiredArgsConstructor
public class RoomController {
    private final RoomFacade roomFacade;

    @PostMapping("/create")
    public CreateRoomResponseDto createRoom(@Valid @RequestBody CreateRoomRequestDto dto) {
        LOGGER.info("Creating room with data: {}", dto);

        final var roomName = roomFacade.createRoom(dto);

        return new CreateRoomResponseDto()
                .setRoomName(roomName);
    }

    @PostMapping("/join")
    public HttpStatus joinToRoom(@Valid @RequestBody JoinRoomRequestDto dto) {
        LOGGER.info("Joining room with data: {}", dto);

        roomFacade.joinRoom(dto);
        return HttpStatus.OK;
    }
}
