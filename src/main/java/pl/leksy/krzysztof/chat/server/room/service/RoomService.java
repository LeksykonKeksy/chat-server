package pl.leksy.krzysztof.chat.server.room.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import pl.leksy.krzysztof.chat.server.room.exceptions.InvalidRoomPasswordException;
import pl.leksy.krzysztof.chat.server.room.exceptions.RoomFullException;
import pl.leksy.krzysztof.chat.server.room.exceptions.RoomNotFoundException;
import pl.leksy.krzysztof.chat.server.room.model.Room;
import pl.leksy.krzysztof.chat.server.room.repository.RoomRepository;
import pl.leksy.krzysztof.chat.server.web.dto.CreateRoomRequestDto;
import pl.leksy.krzysztof.chat.server.web.dto.JoinRoomRequestDto;
import pl.leksy.krzysztof.chat.server.web.dto.RoomListDto;
import pl.leksy.krzysztof.chat.server.web.dto.RoomListElementDto;

import javax.annotation.PostConstruct;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class RoomService implements RoomFacade {
    private final RoomRepository roomRepository;
    private final Map<String, Integer> roomUsedSlots = new ConcurrentHashMap<>();

    @PostConstruct
    public void init() {
        roomRepository.findAll()
                .forEach(r -> roomUsedSlots.put(r.getRoomName(), 0));
    }

    @Override
    public String createRoom(CreateRoomRequestDto dto) {
        LOGGER.info("Trying to create room with name {}", dto.getName());

        var roomName = getValidUniqueRoomName(dto.getName());

        final var newRoom = new Room()
                .setRoomName(roomName)
                .setPasswordProtected(dto.getPasswordProtected())
                .setPassword(dto.getPassword())
                .setSlots(dto.getSlots())
                .setCreator(dto.getNickname());

        roomUsedSlots.put(roomName, 0);

        roomRepository.save(newRoom);

        LOGGER.info("Successfully created room with name {}", newRoom.getRoomName());

        return roomName;
    }

    @Override
    public void joinRoom(JoinRoomRequestDto dto) {
        final var roomName = dto.getName();

        LOGGER.info("Trying to join to room with name {}", roomName);

        final var foundRoom = roomRepository.findById(roomName).orElseThrow(RoomNotFoundException::new);

        if (foundRoom.isPasswordProtected()) {
            if (!foundRoom.getPassword().equals(dto.getPassword())) {
                throw new InvalidRoomPasswordException();
            }
        }

        addToRoomSlots(roomName, foundRoom.getSlots()); // TODO: przesunąć to do momentu faktycznego dołączenia do pokoju
        // TODO: dodać usuwanie zajętych slotów po wyjściu z pokoju

        LOGGER.info("User {} can join to room {}", dto.getNickname(), roomName);
    }

    @Override
    public RoomListDto getPublicRooms() {
        return new RoomListDto()
                .setRooms(roomRepository.findAll()
                        .stream()
                        .filter(r -> !r.isPasswordProtected())
                        .map(r -> new RoomListElementDto()
                                .setName(r.getRoomName())
                                .setCreatedBy(r.getCreator())
                                .setSlots(r.getSlots())
                                .setCurrentUsers(roomUsedSlots.get(r.getRoomName())))
                        .collect(Collectors.toList()));
    }

    private String getValidUniqueRoomName(final String originalName) {
        var roomName = originalName;
        if (originalName == null || originalName.isBlank()) {
            roomName = generateName();
        }

        var foundRoom = roomRepository.findById(roomName);

        while (foundRoom.isPresent()) {
            roomName = generateName();
            foundRoom = roomRepository.findById(roomName);
        }

        return roomName;
    }

    private String generateName() {
        return UUID.randomUUID()
                .toString()
                .replace("-", "")
                .substring(0, 10);
    }

    private void addToRoomSlots(String roomName, int maxSlots) {
        if (roomUsedSlots.containsKey(roomName)) {
            final var roomCurrentUsers = roomUsedSlots.get(roomName);
            if (roomCurrentUsers < maxSlots) {
                //noinspection ConstantConditions
                roomUsedSlots.compute(roomName, (k, v) -> v + 1);
            } else {
                throw new RoomFullException();
            }
        } else {
            throw new RoomNotFoundException();
        }
    }
}
