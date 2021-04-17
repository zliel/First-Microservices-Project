package com.example.MusicReviewService.models;

public class MusicReview { // Equivalent to koushikkothagal's "Catalog Item"

    private String name;
    private String composerName;
    private String review;
    private int rating;

    public MusicReview(String name, String composerName, String review, int rating) {
        this.name = name;
        this.composerName = composerName;
        this.review = review;
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComposerName() {
        return composerName;
    }

    public void setComposerName(String composerName) {
        this.composerName = composerName;
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
}
