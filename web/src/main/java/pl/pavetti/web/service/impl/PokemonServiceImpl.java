package pl.pavetti.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.pavetti.web.dto.PokemonDto;
import pl.pavetti.web.exception.PokemonNotFoundException;
import pl.pavetti.web.model.Pokemon;
import pl.pavetti.web.repository.PokemonRepository;
import pl.pavetti.web.service.PokemonService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PokemonServiceImpl implements PokemonService {

    private final PokemonRepository pokemonRepository;
    @Autowired
    public PokemonServiceImpl(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    @Override
    public PokemonDto createPokemon(PokemonDto pokemonDto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());

        Pokemon newPokemon = pokemonRepository.save(pokemon);

        PokemonDto pokemonResponse = new PokemonDto();
        pokemonResponse.setId(newPokemon.getId());
        pokemonResponse.setName(newPokemon.getName());
        pokemonResponse.setType(newPokemon.getType());

        return pokemonResponse;
    }

    @Override
    public List<PokemonDto> getAllPokemon() {
        return pokemonRepository.findAll()
                .stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public PokemonDto getPokemonById(int id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow( () ->
                new PokemonNotFoundException("could not find the pokemon with id = " + id));
        return mapToDto(pokemon);
    }

    @Override
    public PokemonDto updatePokemon(PokemonDto pokemonDto, int id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow( () ->
                new PokemonNotFoundException("could not find the pokemon with id = " + id));
        pokemon.setName(pokemonDto.getName());
        pokemon.setType(pokemonDto.getType());

        Pokemon updatedPokeomn =  pokemonRepository.save(pokemon);
        return mapToDto(updatedPokeomn);
    }

    @Override
    public void deletePokemon(int id) {
        Pokemon pokemon = pokemonRepository.findById(id).orElseThrow( () ->
                new PokemonNotFoundException("could not find the pokemon with id = " + id));
        pokemonRepository.delete(pokemon);
    }

    private PokemonDto mapToDto(Pokemon pokemon){
        return new PokemonDto(
                pokemon.getId(),
                pokemon.getName(),
                pokemon.getType()
        );
    }

    private Pokemon mapToEntitu(PokemonDto pokemonDto){
        return new Pokemon(
                pokemonDto.getId(),
                pokemonDto.getName(),
                pokemonDto.getType()
        );
    }
}
