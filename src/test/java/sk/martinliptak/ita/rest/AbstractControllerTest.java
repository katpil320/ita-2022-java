package sk.martinliptak.ita.rest;

import org.springframework.context.annotation.Import;
import sk.martinliptak.ita.configuration.SecurityConfiguration;
import sk.martinliptak.ita.configuration.SecurityConfigurationProperties;

import java.io.IOException;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Objects;

@Import({SecurityConfiguration.class, SecurityConfigurationProperties.class})
public abstract class AbstractControllerTest {
    public String getJsonContent(String resources) throws IOException, URISyntaxException {
        return Files.readString(Paths.get(Objects.requireNonNull(getClass().getResource(resources)).toURI()));
    }
}
