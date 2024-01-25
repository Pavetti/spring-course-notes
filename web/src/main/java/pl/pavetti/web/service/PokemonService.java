package pl.pavetti.web.service;

import pl.pavetti.web.dto.PokemonDto;
import pl.pavetti.web.dto.PokemonResponse;


public interface PokemonService {
    PokemonDto createPokemon(PokemonDto pokemonDto);
    PokemonResponse getAllPokemon(int pageNo, int pageSize);
    PokemonDto getPokemonById(int id);
    PokemonDto updatePokemon(PokemonDto pokemonDto, int id);
    void deletePokemon(int id);
}
