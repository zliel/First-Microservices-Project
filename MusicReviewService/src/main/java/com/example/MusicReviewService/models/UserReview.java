package com.example.MusicReviewService.models;

import java.util.ArrayList;
import java.util.List;

public class UserReview {
    private String userId;
    private List<Review> reviews = new ArrayList<>();

    public UserReview() {
    }

    public UserReview(String userId, List<Review> reviews) {
        this.userId = userId;
        this.reviews = reviews;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public List<Review> getReviews() {
        return reviews;
    }

    public void setReviews(List<Review> reviews) {
        this.reviews = reviews;
    }

    @Override
    public String toString() {
        return "UserReview{" +
                "userId='" + userId + '\'' +
                ", reviews=" + reviews +
                '}';
    }
}
