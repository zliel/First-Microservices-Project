package com.example.UserReviewService;

import com.example.UserReviewService.models.Review;
import com.example.UserReviewService.models.UserReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class UserReviewServiceController {
    @Autowired
    WebClient.Builder webClientBuilder;

    @GetMapping("/api/userReview/{userId}")
    public Mono<UserReview> getUserReview(@PathVariable("userId") String userId) {


        Flux<Review> reviews = webClientBuilder.build().get()
                .uri("http://user-review-service/flux")
                .retrieve()
                .bodyToFlux(Review.class);

        // Solution came from: http://www.yuanmacha.com/13435351963.html
        /*
            Problem: Originally, I was attempting to store a Flux<Review> inside of the UserReview object, but because reactive types aren't
            meant to be serialized, they aren't able to be passed as an instance field when returning something from a REST controller.

            Solution: We need to retrieve the Flux<Review> from the "/flux" endpoint, and, without blocking, collect the list (using collectList(),
            which returns a Mono<List<T>>), and then map over the mono, accessing the list and passing it in to the constructor for the UserReview object
         */
        return reviews.collectList().map(reviewList -> new UserReview(userId, reviewList));
    }

    @GetMapping("/flux")
    public Flux<Review> getReviews() {
        return Flux.just(
                new Review("Composition 1", "It was okay", 3),
                new Review("Composition 2", "Better than 1", 4),
                new Review("Composition 3", "Awful", 1)
        );
    }
}
