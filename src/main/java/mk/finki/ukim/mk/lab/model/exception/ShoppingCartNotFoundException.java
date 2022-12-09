package mk.finki.ukim.mk.lab.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class ShoppingCartNotFoundException extends RuntimeException{
    public ShoppingCartNotFoundException(Long id) {
        super(String.format("Shopping card with id: %d was not found", id));
    }
}
