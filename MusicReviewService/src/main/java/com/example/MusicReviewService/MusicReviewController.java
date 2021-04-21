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
        // ORDER:
        //      UserReview
        //      return Flux.from(UserReview.getReviews()).map(.
        //          Composition
        //          return new MusicReview()
        //      );

        // Make a WebClient request to the UserReviewService using the given userId and retrieve a Mono containing the user's reviews
        Mono<UserReview> userReviewMono = webClientBuilder.build().get().uri("http://user-review-service/api/userReview/" + userId)
                                         .retrieve()
                                         .bodyToMono(UserReview.class);

        // Retrieve the list of the user's reviews from the userReviewMono and store its items in a flux
        Flux<Review> reviews = userReviewMono.flatMapIterable(UserReview::getReviews);

        // map over our list, creating new MusicReview instances from each review and storing them inside a Flux, which we return
        return reviews.flatMap(review -> {
            // Retrieve the Mono<Composition> for our MusicReview instances using a WebClient call to our Composition Service with the composition ID from the current review
            Mono<Composition> composition = webClientBuilder.build().get().uri("http://composition-service/api/" + review.getCompositionId())
                                                            .retrieve()
                                                            .bodyToMono(Composition.class);

            // Map over the returned Mono<Composition>, creating a new MusicReview instance, and using the properties from the
            // current composition and review to create and return a new MusicReview (stored in a Mono). Because we used flatMap(), this
            // will be flattened into a Flux<MusicReview>
            return composition.map(
                    comp -> new MusicReview(comp.getName(), comp.getComposer(), review.getReview(), review.getRating())
            );
        });
    }
}
