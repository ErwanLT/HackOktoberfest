package fr.eletutour.hackOktoberfest.services;

import fr.eletutour.hackOktoberfest.domain.beer.Brewery;
import fr.eletutour.hackOktoberfest.exceptions.BreweryException;
import fr.eletutour.hackOktoberfest.repository.AdressRepository;
import fr.eletutour.hackOktoberfest.repository.BreweryRepository;
import org.springframework.stereotype.Service;

import java.util.List;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class BreweryService {

    private final BreweryRepository breweryRepository;
    private final AdressRepository adressRepository;

    public BreweryService(BreweryRepository breweryRepository, AdressRepository adressRepository) {
        this.breweryRepository = breweryRepository;
        this.adressRepository = adressRepository;
    }

    public List<Brewery> getAllBrewery() {
        return breweryRepository.findAll();
    }

    public Brewery getOneBrewery(Long id) {
        return breweryRepository.findById(id).orElseThrow(() -> new BreweryException(String.format("La brasserie %s n'existe pas", id), NOT_FOUND));
    }

    public Brewery createBrewery(Brewery brewery) {
        var b = breweryRepository.findByName(brewery.getName());
        if(b != null){
            throw new BreweryException(String.format("La brasserie %s existe déjà", b.getName()), BAD_REQUEST);
        }

        var address = adressRepository.save(brewery.getAdress());
        brewery.setAdress(address);

        return breweryRepository.save(brewery);
    }

    public Brewery updateBrewery(Brewery breweryToUpdate) {
        var b = getOneBrewery(breweryToUpdate.getId());
        var a = adressRepository.findById(breweryToUpdate.getAdress().getId()).orElseThrow(() -> new BreweryException("L'adresse associée n'existe, veuillez la créer avant.", BAD_REQUEST));

        var adresseToUpdate = breweryToUpdate.getAdress();
        a.setId(adresseToUpdate.getId());
        a.setCity(adresseToUpdate.getCity());
        a.setDenomination(adresseToUpdate.getDenomination());
        a.setEntrance(adresseToUpdate.getEntrance());
        a.setIdentity(adresseToUpdate.getIdentity());
        a.setStreet(adresseToUpdate.getStreet());
        a.setPostalCode(adresseToUpdate.getPostalCode());

        a = adressRepository.save(a);

        b.setName(breweryToUpdate.getName());
        b.setAdress(a);

        return breweryRepository.save(b);
    }
}
