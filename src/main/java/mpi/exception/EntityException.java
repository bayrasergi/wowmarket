package mpi.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class EntityException extends RuntimeException {

    private HttpStatus httpStatus;
    private Object entity;

    public EntityException(String message, HttpStatus httpStatus, Object entity) {
        super(message);
        this.httpStatus = httpStatus;
        this.entity = entity;
        setStackTrace(new StackTraceElement[0]);
    }
}
