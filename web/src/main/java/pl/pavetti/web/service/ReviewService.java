package pl.pavetti.web.service;

import pl.pavetti.web.dto.ReviewDto;

import java.util.List;

public interface ReviewService {
    ReviewDto createReview(int pokemonId, ReviewDto reviewDto);
    List<ReviewDto> getReviewsByPokemonId(int id);
    ReviewDto getReviewById(int reviewId, int pokemonId);
    ReviewDto updateReview(int reviewId, int pokemonId, ReviewDto reviewDto);
    void deleteReview(int reviewId, int pokemonId);
}
