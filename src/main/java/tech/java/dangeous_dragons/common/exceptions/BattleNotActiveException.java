package tech.java.dangeous_dragons.common.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class BattleNotActiveException extends RuntimeException {
    public BattleNotActiveException() {
        super("The battle is no longer active.");
    }
}