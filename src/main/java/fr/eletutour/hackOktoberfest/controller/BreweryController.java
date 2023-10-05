package fr.eletutour.hackOktoberfest.controller;

import fr.eletutour.hackOktoberfest.domain.beer.Brewery;
import fr.eletutour.hackOktoberfest.exceptions.ErrorResponse;
import fr.eletutour.hackOktoberfest.services.BreweryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/breweries")
@Tag(name = "Brewery service", description = "manage breweries")
public class BreweryController {

    private final BreweryService breweryService;

    public BreweryController(BreweryService breweryService) {
        this.breweryService = breweryService;
    }

    @Operation(summary = "Get all brewery")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found all brewery",
                    content = { @Content(mediaType = "application/json",
                            array = @ArraySchema(schema = @Schema(implementation = Brewery.class)))}
    )})
    @GetMapping
    public List<Brewery> getAllBrewery() {
        return breweryService.getAllBrewery();
    }

    @Operation(summary = "Get a brewery by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the brewery",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Brewery.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "brewery not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @GetMapping("/{id}")
    public Brewery getBrewery(@PathVariable Long id){
        return breweryService.getOneBrewery(id);
    }

    @Operation(summary = "Create a brewery")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Brewery created",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Brewery.class)) }),
            @ApiResponse(responseCode = "400", description = "Brewery already exist",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class)))
    })
    @PostMapping
    public Brewery createBrewery(@RequestBody Brewery brewery){
        return breweryService.createBrewery(brewery);
    }

    @Operation(summary = "Update a brewery")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Brewery updated",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Brewery.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid address supplied",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))),
            @ApiResponse(responseCode = "404", description = "brewery not found",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ErrorResponse.class))) })
    @PutMapping("/{id}")
    public Brewery updateBrewery(@RequestBody Brewery breweryToUpdate){
        return breweryService.updateBrewery(breweryToUpdate);
    }

}
