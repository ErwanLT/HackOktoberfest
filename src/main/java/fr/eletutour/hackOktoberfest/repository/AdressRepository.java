package fr.eletutour.hackOktoberfest.repository;

import fr.eletutour.hackOktoberfest.domain.beer.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdressRepository extends JpaRepository<Address, Long> {
}
