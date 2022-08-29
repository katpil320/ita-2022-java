package sk.martinliptak.ita.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ExceptionDto {
    private final String code;
    private final String message;
}
