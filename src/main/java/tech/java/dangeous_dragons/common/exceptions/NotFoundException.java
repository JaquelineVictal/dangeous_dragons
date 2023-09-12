package tech.java.dangeous_dragons.common.exceptions;

import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@Getter
@ResponseStatus(HttpStatus.NOT_FOUND)
public class NotFoundException extends RuntimeException {
    private final String entityNotFound;

    public NotFoundException(String entityNotFound) {
        this.entityNotFound = entityNotFound;
    }

}