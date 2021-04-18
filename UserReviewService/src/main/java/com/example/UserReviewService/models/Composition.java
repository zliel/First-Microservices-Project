package com.example.UserReviewService.models;

public class Composition {
    private String compositionId;
    private String name;
    private String composer;

    public Composition(String compositionId, String name, String composer) {
        this.compositionId = compositionId;
        this.name = name;
        this.composer = composer;
    }

    public String getCompositionId() {
        return compositionId;
    }

    public void setCompositionId(String compositionId) {
        this.compositionId = compositionId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }


    @Override
    public String toString() {
        return "Composition{" +
                "compositionId='" + compositionId + '\'' +
                ", name='" + name + '\'' +
                ", composer='" + composer + '\'' +
                '}';
    }
}
