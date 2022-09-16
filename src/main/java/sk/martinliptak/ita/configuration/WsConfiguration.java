package sk.martinliptak.ita.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.oxm.jaxb.Jaxb2Marshaller;
import sk.martinliptak.ita.ws.AresClient;
import sk.martinliptak.ita.ws.AresClientImpl;

@Configuration
public class WsConfiguration {
    @Bean
    public Jaxb2Marshaller requestMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("cz.ares.request");
        return marshaller;
    }

    @Bean
    public Jaxb2Marshaller responseUnMarshaller() {
        Jaxb2Marshaller marshaller = new Jaxb2Marshaller();
        marshaller.setContextPath("cz.ares.response");
        return marshaller;
    }

    @Bean
    public AresClient aresWsClient(Jaxb2Marshaller requestMarshaller,
                                   Jaxb2Marshaller responseUnMarshaller) {
        AresClientImpl client = new AresClientImpl();
        client.setDefaultUri("http://wwwinfo.mfcr.cz/cgi-bin/ares/xar.cgi");
        client.setMarshaller(requestMarshaller);
        client.setUnmarshaller(responseUnMarshaller);
        return client;
    }
}
