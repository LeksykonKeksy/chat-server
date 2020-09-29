package pl.leksy.krzysztof.chat.server.room.model;

import lombok.AccessLevel;
import lombok.Data;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "room")
public class Room {
    @Id
    @Column(name = "room_name", length = 32, nullable = false, unique = true, updatable = false)
    private String roomName;

    @Column(nullable = false, updatable = false)
    private int slots;

    @Column(name = "password_protected", updatable = false, nullable = false)
    private boolean passwordProtected;

    @Column(updatable = false, length = 60)
    private String password;

    @Column(nullable = false, updatable = false)
    private String creator;

    @CreatedDate
    @Setter(AccessLevel.NONE)
    private LocalDateTime createdAt;
}
