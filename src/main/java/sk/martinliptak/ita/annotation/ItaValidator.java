package sk.martinliptak.ita.annotation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ItaValidator implements ConstraintValidator<StartsWithUppercase, String> {
    public boolean isValid(String text, ConstraintValidatorContext cxt) {
        return text.charAt(0) == Character.toUpperCase(text.charAt(0));
    }
}
