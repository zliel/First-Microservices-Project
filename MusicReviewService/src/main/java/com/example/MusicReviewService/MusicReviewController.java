package com.example.MusicReviewService;

import com.example.MusicReviewService.models.Composition;
import com.example.MusicReviewService.models.MusicReview;
import com.example.MusicReviewService.models.Review;
import com.example.MusicReviewService.models.UserReview;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
public class MusicReviewController {

    @Autowired
    WebClient.Builder webClientBuilder;

    @GetMapping("/api/musicReview/{userId}")
    public Flux<MusicReview> getMusicReview(@PathVariable("userId") String userId) {
        // This will come from a webClient request using the Review.getCompositionId() method (will be inside the return statement
        Composition testComposition = new Composition("test1", "Nocturne No. 0", "Choppin");

        // ORDER:
        //      UserReview
        //      return Flux.from(UserReview.getReviews()).map(.
        //          Composition
        //          return new MusicReview()
        //      );

        // Make a WebClient request to the UserReviewService using the given userId and retrieve a Mono containing the user's reviews
        Mono<UserReview> userReviewMono = webClientBuilder.build().get().uri("http://localhost:8083/api/userReview/" + userId)
                                         .retrieve()
                                         .bodyToMono(UserReview.class);

        // Retrieve the list of the user's reviews from the userReviewMono and store its items in a flux
        Flux<Review> reviews = userReviewMono.flatMapIterable(UserReview::getReviews);

        // map over our list, creating new MusicReview instances from each review and storing them inside a Flux, which we return
        return reviews.map(review -> {
            System.out.println(review);

            return new MusicReview(testComposition.getName(), testComposition.getComposer(), review.getReview(), review.getRating());
        });
    }
}
