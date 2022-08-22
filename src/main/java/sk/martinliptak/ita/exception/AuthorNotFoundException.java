package sk.martinliptak.ita.exception;

import org.springframework.http.HttpStatus;

public class AuthorNotFoundException extends ItaException {
    public AuthorNotFoundException(Long id) {
        super("Author " + id + " not found!", "0001", HttpStatus.NOT_FOUND);
    }
}

