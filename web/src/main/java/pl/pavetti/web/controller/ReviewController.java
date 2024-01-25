package pl.pavetti.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.pavetti.web.dto.ReviewDto;
import pl.pavetti.web.service.ReviewService;

import java.util.List;

@RestController
@RequestMapping("/api/")
public class ReviewController {

    private ReviewService reviewService;

    @Autowired
    public ReviewController(ReviewService reviewService) {
        this.reviewService = reviewService;
    }

    @PostMapping("pokemon/{pokemonId}/reviews")
    public ResponseEntity<ReviewDto> createReview(
            @RequestBody ReviewDto reviewDto,
            @PathVariable(value = "pokemonId") int pokemonId
    ){
        return new ResponseEntity<>(reviewService.createReview(pokemonId,reviewDto), HttpStatus.CREATED);
    }

    @GetMapping("pokemon/{pokemonId}/reviews")
    public ResponseEntity<List<ReviewDto>> getReviews(
            @PathVariable(value = "pokemonId") int pokemonId
    ){
        return new ResponseEntity<>(reviewService.getReviewsByPokemonId(pokemonId), HttpStatus.OK);
    }

    @GetMapping("pokemon/{pokemonId}/reviews/{id}")
    public ResponseEntity<ReviewDto> getReview(
            @PathVariable(value = "pokemonId") int pokemonId,
            @PathVariable(value = "id") int id
    ){
        return new ResponseEntity<>(reviewService.getReviewById(id,pokemonId),HttpStatus.OK);
    }

    @PutMapping("pokemon/{pokemonId}/reviews/{id}")
    public ResponseEntity<ReviewDto> updateReview(
            @PathVariable(value = "pokemonId") int pokemonId,
            @PathVariable(value = "id") int id,
            @RequestBody ReviewDto reviewDto
    ){
        return new ResponseEntity<>(reviewService.updateReview(id,pokemonId,reviewDto),HttpStatus.OK);
    }

    @DeleteMapping("pokemon/{pokemonId}/reviews/{id}")
    public ResponseEntity<String> deleteReview(
            @PathVariable(value = "pokemonId") int pokemonId,
            @PathVariable(value = "id") int id
    ){
        reviewService.deleteReview(id,pokemonId);
        return ResponseEntity.ok("review deleted");
    }
}
