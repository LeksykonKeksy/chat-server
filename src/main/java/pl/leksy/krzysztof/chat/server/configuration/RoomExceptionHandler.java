package pl.leksy.krzysztof.chat.server.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import pl.leksy.krzysztof.chat.server.room.exceptions.InvalidRoomPasswordException;
import pl.leksy.krzysztof.chat.server.room.exceptions.RoomFullException;
import pl.leksy.krzysztof.chat.server.room.exceptions.RoomNotFoundException;
import pl.leksy.krzysztof.chat.server.web.controller.RoomController;

@Slf4j
@ControllerAdvice(assignableTypes = RoomController.class)
public class RoomExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(RoomNotFoundException.class)
    public HttpStatus handleRoomNotFoundException(Throwable e) {
        LOGGER.error("Room not found");
        return HttpStatus.NOT_FOUND;
    }

    @ExceptionHandler(RoomFullException.class)
    public HttpStatus handleRoomFullException(Throwable e) {
        LOGGER.error("Room is full");
        return HttpStatus.CONFLICT;
    }

    @ExceptionHandler(InvalidRoomPasswordException.class)
    public HttpStatus handleInvalidRoomPasswordException(Throwable e) {
        LOGGER.error("Room password does not match");
        return HttpStatus.UNAUTHORIZED;
    }

    @ExceptionHandler(Exception.class)
    public HttpStatus defaultHandleException(Throwable e) {
        LOGGER.error("Unknown exception: ", e);
        return HttpStatus.INTERNAL_SERVER_ERROR;
    }
}
