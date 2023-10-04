package fr.eletutour.hackOktoberfest.controller;

import fr.eletutour.hackOktoberfest.domain.beer.Brewery;
import fr.eletutour.hackOktoberfest.services.BreweryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/breweries")
public class BreweryController {

    private final BreweryService breweryService;

    public BreweryController(BreweryService breweryService) {
        this.breweryService = breweryService;
    }

    @GetMapping
    public List<Brewery> getAllBrewery() {
        return breweryService.getAllBrewery();
    }

    @GetMapping("/{id}")
    public Brewery getBrewery(@PathVariable Long id){
        return breweryService.getOneBrewery(id);
    }

    @PostMapping
    public Brewery createBrewery(@RequestBody Brewery brewery){
        return breweryService.createBrewery(brewery);
    }

    @PutMapping("/{id}")
    public Brewery updateBrewery(@RequestBody Brewery breweryToUpdate){
        return breweryService.updateBrewery(breweryToUpdate);
    }

}
