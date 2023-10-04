package fr.eletutour.hackOktoberfest.repository;

import fr.eletutour.hackOktoberfest.domain.beer.Brewery;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BreweryRepository extends JpaRepository<Brewery, Long> {

    Brewery findByName(String name);
}
