package pl.leksy.krzysztof.chat.server.room.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

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
}
