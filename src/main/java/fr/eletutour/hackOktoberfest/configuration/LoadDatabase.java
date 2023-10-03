package fr.eletutour.hackOktoberfest.configuration;

import fr.eletutour.hackOktoberfest.domain.user.AppUser;
import fr.eletutour.hackOktoberfest.repository.UserRepository;
import org.springframework.context.annotation.Configuration;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;

@Configuration
public class LoadDatabase {

    private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);

    @Bean
    CommandLineRunner initDatabase(UserRepository repository) {

        return args -> {
            log.info("Preloading " + repository.save(new AppUser("Erwan", "erwan@test.com", "passwordtest")));
        };
    }

}
