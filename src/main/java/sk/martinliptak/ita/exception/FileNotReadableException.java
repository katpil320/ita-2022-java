package sk.martinliptak.ita.exception;

import org.springframework.http.HttpStatus;

public class FileNotReadableException extends ItaException {
    public FileNotReadableException() {
        super("File is not readable", "0000", HttpStatus.BAD_REQUEST);
    }
}
