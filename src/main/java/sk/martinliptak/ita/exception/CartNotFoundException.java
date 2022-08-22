package sk.martinliptak.ita.exception;

import org.springframework.http.HttpStatus;

public class CartNotFoundException extends ItaException {
    public CartNotFoundException(Long id) {
        super("Cart " + id + " not found!", "0001", HttpStatus.NOT_FOUND);
    }
}
