package sk.martinliptak.ita.exception;

import org.springframework.http.HttpStatus;

public class GenreNotFoundException extends ItaException {
    public GenreNotFoundException(Long id) {
        super("Genre " + id + " not found!", "0001", HttpStatus.NOT_FOUND);
    }
}
