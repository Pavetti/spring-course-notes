package pl.pavetti.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pavetti.web.model.Pokemon;

public interface PokemonRepository extends JpaRepository<Pokemon,Integer> {

}
