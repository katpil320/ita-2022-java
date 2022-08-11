package sk.martinliptak.ita.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.Objects;

public class ItaValidator implements ConstraintValidator<StartsWithUppercase, String> {
    public boolean isValid(String text, ConstraintValidatorContext cxt) {
        if (Objects.isNull(text) || text.isEmpty()) {
            return false;
        }
        return text.charAt(0) == Character.toUpperCase(text.charAt(0));
    }
}
