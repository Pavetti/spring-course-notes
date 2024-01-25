package pl.pavetti.web.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pavetti.web.model.Pokemon;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/")
public class PokemonController {

    @GetMapping("pokemon")
    public ResponseEntity<List<Pokemon>> getPokemon(){
        List<Pokemon> pokemons = new ArrayList<>();
        pokemons.add(new Pokemon(1,"Pikachu","electric"));
        pokemons.add(new Pokemon(2,"Squirtle","water"));
        pokemons.add(new Pokemon(3,"Charmander","fire"));
        return ResponseEntity.ok(pokemons);

    }

    @GetMapping("pokemon/{id}")
    public Pokemon getPokemonById(@PathVariable int id){
        return new Pokemon(id,"Charmander","fire");
    }

    @PostMapping("pokemon/create")
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Pokemon> createPokemon(@RequestBody Pokemon pokemon){
        System.out.println(pokemon.getId());
        System.out.println(pokemon.getName());
        System.out.println(pokemon.getType());
        return new ResponseEntity<>(pokemon,HttpStatus.CREATED);
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
