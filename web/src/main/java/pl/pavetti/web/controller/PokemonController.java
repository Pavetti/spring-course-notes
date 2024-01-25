package pl.pavetti.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pavetti.web.dto.PokemonDto;
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
    public ResponseEntity<List<PokemonDto>> getPokemon(){
        return ResponseEntity.ok(pokemonService.getAllPokemon());
    }

    @GetMapping("pokemon/{id}")
    public Pokemon getPokemonById(@PathVariable int id){
        return new Pokemon(id,"Charmander","fire");
    }

    @PostMapping("pokemon/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonDto> createPokemon(@RequestBody PokemonDto pokemonDto){
        return new ResponseEntity<>(pokemonService.createPokemon(pokemonDto),HttpStatus.CREATED);
    }

    @PostMapping("pokemon/{id}/update")
    public ResponseEntity<Pokemon> updatePokemon(@RequestBody Pokemon pokemon, @PathVariable("id") int id){
        System.out.println(pokemon.getId());
        System.out.println(pokemon.getName());
        System.out.println(pokemon.getType());
        pokemon.setName("chujek");
        System.out.println("new name" + pokemon.getName());
        return ResponseEntity.ok(pokemon);
    }
}
