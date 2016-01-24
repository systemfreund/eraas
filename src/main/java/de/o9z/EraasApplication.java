package de.o9z;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class EraasApplication {

    public static void main(String[] args) {
        SpringApplication.run(EraasApplication.class, args);
    }

    @Bean
    ReactionsService reactionsService() {
        return new InMemoryReactions();
    }

}
