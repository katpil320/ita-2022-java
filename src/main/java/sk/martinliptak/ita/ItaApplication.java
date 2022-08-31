package sk.martinliptak.ita;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class ItaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ItaApplication.class, args);
    }

}
