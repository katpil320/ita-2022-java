package sk.martinliptak.ita.annotation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

import static java.lang.annotation.ElementType.FIELD;

@Target(FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = ItaValidator.class)
public @interface StartsWithUppercase {
    String message() default "Field must start with uppercase letter!";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
