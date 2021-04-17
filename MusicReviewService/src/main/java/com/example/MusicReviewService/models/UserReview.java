package com.example.MusicReviewService.models;

import reactor.core.publisher.Flux;

public class UserReview {
    private String userId;
//    private Composition composition;
    private Flux<Review> reviews;

    public UserReview(String userId, /*Composition composition,*/ Flux<Review> reviews) {
        this.userId = userId;
//        this.composition = composition;
        this.reviews = reviews;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

//    public Composition getComposition() {
//        return composition;
//    }
//
//    public void setComposition(Composition composition) {
//        this.composition = composition;
//    }

    public Flux<Review> getReviews() {
        return reviews;
    }

    public void setReviews(Flux<Review> reviews) {
        this.reviews = reviews;
    }
}
