package com.example.MusicReviewService;

import com.example.MusicReviewService.models.Composition;
import com.example.MusicReviewService.models.MusicReview;
import com.example.MusicReviewService.models.Review;
import com.example.MusicReviewService.models.UserReview;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class MusicReviewController {

    @GetMapping("/api/musicReview/{userId}")
    public Flux<MusicReview> getMusicReview(@PathVariable("userId") String userId) {
        // This will come from a webClient request using the Review.getCompositionId() method (will be inside the return statement
        Composition testComposition = new Composition("test1", "Nocturne No. 0", "Choppin");

        // This will be retrieved before the Composition, and will be retrieved using the UserReview.getReviews() method
        Flux<Review> testReviewFlux = Flux.just(new Review(testComposition.getCompositionId(), "It sucked", 4));

        // This will be retrieved first using a webClient request with the user's ID
        UserReview testReview = new UserReview(userId, testReviewFlux);

        // ORDER:
        //      UserReview
        //      return Flux.from(UserReview.getReviews()).map(.
        //          Composition
        //          return new MusicReview()
        //      );

        return Flux.from(testReview.getReviews()).map(review -> {
            // In the actual program, we'll get the Composition necessary by making a request using the "review.getCompositionId()" method
            return new MusicReview(testComposition.getName(), testComposition.getComposer(), review.getReview(), review.getRating());
        });
    }
}
