package sk.martinliptak.ita.rest;

import org.springframework.context.annotation.Import;
import sk.martinliptak.ita.configuration.SecurityConfiguration;
import sk.martinliptak.ita.configuration.SecurityConfigurationProperties;

@Import({SecurityConfiguration.class, SecurityConfigurationProperties.class})
public abstract class AbstractControllerTest {
}
