package sk.martinliptak.ita.exception;

import org.springframework.http.HttpStatus;

public class ProductNotFoundException extends ItaException {
    public ProductNotFoundException(Long id) {
        super("Product " + id + " not found!", "0001", HttpStatus.NOT_FOUND);
    }
}
