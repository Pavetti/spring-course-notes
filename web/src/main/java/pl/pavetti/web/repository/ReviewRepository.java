package pl.pavetti.web.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import pl.pavetti.web.model.Review;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review,Integer> {
    List<Review> findByPokemonId(int pokemonId);
}
