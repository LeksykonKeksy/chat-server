package pl.leksy.krzysztof.chat.server.room.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.leksy.krzysztof.chat.server.room.model.Room;

public interface RoomRepository extends JpaRepository<Room, String> {
}
