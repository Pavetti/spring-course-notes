package pl.pavetti.web.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;
import pl.pavetti.web.dto.ReviewDto;
import pl.pavetti.web.exception.PokemonNotFoundException;
import pl.pavetti.web.exception.ReviewNotFoundException;
import pl.pavetti.web.model.Pokemon;
import pl.pavetti.web.model.Review;
import pl.pavetti.web.repository.PokemonRepository;
import pl.pavetti.web.repository.ReviewRepository;
import pl.pavetti.web.service.ReviewService;

import java.util.List;

@Service
public class ReviewServiceImpl implements ReviewService {

    private final PokemonRepository pokemonRepository;
    private final ReviewRepository reviewRepository;
    @Autowired
    public ReviewServiceImpl(PokemonRepository pokemonRepository, ReviewRepository reviewRepository) {
        this.pokemonRepository = pokemonRepository;
        this.reviewRepository = reviewRepository;
    }


    @Override
    public ReviewDto createReview(int pokemonId, ReviewDto reviewDto) {
        Review review = mapToEntity(reviewDto);
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow( () ->
                new PokemonNotFoundException("could not find the pokemon with id = " + pokemonId));
        review.setPokemon(pokemon);
        Review newReview = reviewRepository.save(review);
        return mapToDto(newReview);
    }

    @Override
    public List<ReviewDto> getReviewsByPokemonId(int id) {
        List<Review> reviews = reviewRepository.findByPokemonId(id);
        List<ReviewDto> reviewDtoList = reviews.stream().map(this::mapToDto).toList();
        return reviewDtoList;
    }

    @Override
    public ReviewDto getReviewById(int reviewId, int pokemonId) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow( () ->
                new PokemonNotFoundException("could not find the pokemon with id = " + pokemonId));
        Review review = reviewRepository.findById(reviewId).orElseThrow(() ->
                new ReviewNotFoundException("could not find review with id = " + reviewId));

        if(review.getPokemon().getId() != pokemon.getId()){
            throw new ReviewNotFoundException("this review does not belong to a pokemon");
        }
        return mapToDto(review);
    }

    @Override
    public ReviewDto updateReview(int reviewId, int pokemonId, ReviewDto reviewDto) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow( () ->
                new PokemonNotFoundException("could not find the pokemon with id = " + pokemonId));
        Review review = reviewRepository.findById(reviewId).orElseThrow(() ->
                new ReviewNotFoundException("could not find review with id = " + reviewId));
        if(review.getPokemon().getId() != pokemon.getId()){
            throw new ReviewNotFoundException("this review does not belong to a pokemon");
        }

        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());

        Review newReview = reviewRepository.save(review);
        return mapToDto(newReview);
    }

    @Override
    public void deleteReview(int reviewId, int pokemonId) {
        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElseThrow( () ->
                new PokemonNotFoundException("could not find the pokemon with id = " + pokemonId));
        Review review = reviewRepository.findById(reviewId).orElseThrow(() ->
                new ReviewNotFoundException("could not find review with id = " + reviewId));
        if(review.getPokemon().getId() != pokemon.getId()){
            throw new ReviewNotFoundException("this review does not belong to a pokemon");
        }

        reviewRepository.delete(review);
    }



    private ReviewDto mapToDto(Review review) {
        ReviewDto reviewDto = new ReviewDto();
        reviewDto.setId(review.getId());
        reviewDto.setTitle(review.getTitle());
        reviewDto.setContent(review.getContent());
        reviewDto.setStars(review.getStars());
        return reviewDto;
    }

    private Review mapToEntity(ReviewDto reviewDto) {
        Review review = new Review();
        review.setId(reviewDto.getId());
        review.setTitle(reviewDto.getTitle());
        review.setContent(reviewDto.getContent());
        review.setStars(reviewDto.getStars());
        return review;
    }
}
