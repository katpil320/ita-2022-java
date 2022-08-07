package sk.martinliptak.ita.rest;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import sk.martinliptak.ita.exception.ItaException;
import sk.martinliptak.ita.model.ExceptionDto;

import java.util.stream.Collectors;

@Component
@ControllerAdvice
@Slf4j
public class ItaCommonControllerAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(ItaException.class)
    public ResponseEntity<Object> handleItaException(ItaException ex, ServletWebRequest request) {
        log.error("An exception occurred while processing " + request.getRequest().getMethod() + " on " + request.getRequest().getRequestURL(), ex);
        return handleExceptionInternal(
                ex,
                new ExceptionDto(ex.getCode(), ex.getMessage()),
                new HttpHeaders(),
                ex.getStatus(),
                request
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Object> handleUnexpectedException(Exception ex, ServletWebRequest request) {
        log.error("An unexpected exception occurred while processing " + request.getRequest().getMethod() + " on " + request.getRequest().getRequestURL(), ex);
        return handleExceptionInternal(
                ex,
                new ExceptionDto("0000", "Generic error"),
                new HttpHeaders(),
                HttpStatus.INTERNAL_SERVER_ERROR,
                request
        );
    }

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        final String errors = ex.getBindingResult().getAllErrors().stream()
                .map(error -> ((FieldError) error).getField() + " " + error.getDefaultMessage())
                .collect(Collectors.joining(", "));
        log.error("An exception occurred during validation", ex);
        return handleExceptionInternal(
                ex,
                new ExceptionDto("0002", errors),
                new HttpHeaders(),
                HttpStatus.BAD_REQUEST,
                request
        );
    }
}
