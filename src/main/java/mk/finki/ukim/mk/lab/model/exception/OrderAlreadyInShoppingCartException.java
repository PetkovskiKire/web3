package mk.finki.ukim.mk.lab.model.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class OrderAlreadyInShoppingCartException extends RuntimeException{
    public OrderAlreadyInShoppingCartException(Long id, String username){
        super(String.format("Order with id: %d for username: %s was not found", id, username));
    }

}
