package sk.martinliptak.ita.model;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public class ExceptionDto {
    private final String code;
    private final String message;
}
