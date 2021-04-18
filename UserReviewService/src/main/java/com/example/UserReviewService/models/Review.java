package com.example.UserReviewService.models;

public class Review {

    private String compositionId;
    private String review;
    private int rating;

    public Review() {
    }

    public Review(String compositionId, String review, int rating) {
        this.compositionId = compositionId;
        this.review = review;
        this.rating = rating;
    }

    public String getCompositionId() {
        return compositionId;
    }

    public void setCompositionId(String compositionId) {
        this.compositionId = compositionId;
    }

    public String getReview() {
        return review;
    }

    public void setReview(String review) {
        this.review = review;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Review{" +
                "compositionId='" + compositionId + '\'' +
                ", review='" + review + '\'' +
                ", rating=" + rating +
                '}';
    }
}
