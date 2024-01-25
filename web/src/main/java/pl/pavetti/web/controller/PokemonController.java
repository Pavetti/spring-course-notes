package pl.pavetti.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pavetti.web.dto.PokemonDto;
import pl.pavetti.web.dto.PokemonResponse;
import pl.pavetti.web.model.Pokemon;
import pl.pavetti.web.service.PokemonService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PokemonController {

    private final PokemonService pokemonService;
    @Autowired
    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @GetMapping("pokemon")
    public ResponseEntity<PokemonResponse> getPokemon(
            @RequestParam(value = "pageNo", defaultValue = "0", required = false) int pageNo,
            @RequestParam(value = "pageSize", defaultValue = "10", required = false) int pageSize
    ){
        return ResponseEntity.ok(pokemonService.getAllPokemon(pageNo,pageSize));
    }

    @GetMapping("pokemon/{id}")
    public ResponseEntity<PokemonDto> getPokemonById(@PathVariable int id){
        return new ResponseEntity<>(pokemonService.getPokemonById(id),HttpStatus.OK);
    }

    @PostMapping("pokemon/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDto> createPokemon(@RequestBody PokemonDto pokemonDto){
        return new ResponseEntity<>(pokemonService.createPokemon(pokemonDto),HttpStatus.CREATED);
    }

    @PutMapping("pokemon/{id}/update")
    public ResponseEntity<PokemonDto> updatePokemon(@RequestBody PokemonDto pokemonDto, @PathVariable("id") int id){
        return ResponseEntity.ok(pokemonService.updatePokemon(pokemonDto,id));
    }

    @DeleteMapping("pokemon/{id}/delete")
    public ResponseEntity<String> deletePokoemon(@PathVariable("id") int id){
        pokemonService.deletePokemon(id);
        return ResponseEntity.ok("Pokemon deleted");
    }
}
