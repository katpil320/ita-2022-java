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
    void stringValid() {
        Boolean result = itaValidator.isValid("An uppercase title", cxt);
        assertThat(result).isTrue();
    }
    @Test
    void stringInvalid() {
        Boolean result = itaValidator.isValid("a lowercase title", cxt);
        assertThat(result).isFalse();
    }
}