package sk.martinliptak.ita.exception;

import org.springframework.http.HttpStatus;

public class OrderNotFoundException extends ItaException {
    public OrderNotFoundException(Long id) {
        super("Order " + id + " not found!", "0001", HttpStatus.NOT_FOUND);
    }
}
