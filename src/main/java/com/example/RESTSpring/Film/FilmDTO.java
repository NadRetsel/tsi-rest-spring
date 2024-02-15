package com.example.RESTSpring.Film;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import java.util.*;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FilmDTO {

    private Integer filmId;
    private String title;
    private String description;
    private Integer languageId;
    private Integer originalLanguageId;
    private Integer rentalDuration;
    private Double rentalRate;
    private Integer length;
    private Integer replacementCost;
    private String rating;
    private String specialFeatures;

    private Set<Integer> actorIds = new HashSet<>();


    public Integer getFilmId() {
        return filmId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public Integer getOriginalLanguageId() {
        return originalLanguageId;
    }

    public Integer getRentalDuration() {
        return rentalDuration;
    }

    public Double getRentalRate() {
        return rentalRate;
    }

    public Integer getLength() {
        return length;
    }

    public Integer getReplacementCost() {
        return replacementCost;
    }

    public String getRating() {
        return rating;
    }

    public String getSpecialFeatures() {
        return specialFeatures;
    }

    public Set<Integer> getActorIds() {
        return actorIds;
    }


    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public void setOriginalLanguageId(Integer originalLanguageId) {
        this.originalLanguageId = originalLanguageId;
    }

    public void setRentalDuration(Integer rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public void setRentalRate(Double rentalRate) {
        this.rentalRate = rentalRate;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public void setReplacementCost(Integer replacementCost) {
        this.replacementCost = replacementCost;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public void setSpecialFeatures(String specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    public void setActorIds(Set<Integer> actorIds) {
        this.actorIds = actorIds;
    }
}
