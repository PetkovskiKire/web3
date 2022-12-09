package mk.finki.ukim.mk.lab.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class InvalidUsernameException extends RuntimeException {
    public InvalidUsernameException(){
        super(String.format("Invalit user name"));
    }
}
