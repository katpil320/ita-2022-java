package sk.martinliptak.ita.annotation;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import javax.validation.ConstraintValidatorContext;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
class ItaValidatorTest {
    @InjectMocks
    private ItaValidator itaValidator;
    @Mock
    private ConstraintValidatorContext cxt;

    @Test
    void isValid() {
        String uppercaseTitle = "Uppercase title";
        String lowercaseTitle = "lowercase title";
        Boolean positiveResult = itaValidator.isValid(uppercaseTitle, cxt);
        Boolean negativeResult = itaValidator.isValid(lowercaseTitle, cxt);

        assertThat(positiveResult).isTrue();
        assertThat(negativeResult).isFalse();
    }
}