package fr.eletutour.hackOktoberfest.configuration;

import fr.eletutour.hackOktoberfest.domain.beer.Address;
import fr.eletutour.hackOktoberfest.domain.beer.Brewery;
import fr.eletutour.hackOktoberfest.domain.user.AppUser;
import fr.eletutour.hackOktoberfest.repository.AdressRepository;
import fr.eletutour.hackOktoberfest.repository.BreweryRepository;
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
    CommandLineRunner initDatabase(UserRepository userRepository, BreweryRepository breweryRepository, AdressRepository adressRepository) {

        return args -> {
            log.info("Preloading " + userRepository.save(new AppUser("Erwan", "erwan@test.com", "passwordtest")));
            log.info("Preloading " + breweryRepository.save(createBrewery(adressRepository)));
        };
    }

    private Brewery createBrewery(AdressRepository adressRepository) {
        Brewery brewery = new Brewery();
        brewery.setName("Brasserie Parisis");

        Address address = new Address();
        address.setDenomination("Parisis");
        address.setIdentity("Brasserie Parisis");
        address.setStreet("3 Rue René Dumont");
        address.setCity("Combs-la-Ville");
        address.setPostalCode("77380");

        adressRepository.save(address);

        brewery.setAdress(address);

        return brewery;
    }

}
