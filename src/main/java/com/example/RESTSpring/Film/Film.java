package com.example.RESTSpring.Film;

import com.example.RESTSpring.Actor.Actor;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name="film")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "film_id")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Film {
    @Id
    @Column(name="film_id",unique=true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer filmId;

    @Column(name="title")
    private String title;

    @Column(name="description")
    private String description;

    @Column(name="language_id")
    private Integer languageId;

    @Column(name="original_language_id")
    private Integer originalLanguageId;

    @Column(name="rental_duration")
    private Integer rentalDuration;

    @Column(name="rental_rate")
    private Double rentalRate;

    @Column(name="length")
    private Integer length;

    @Column(name="replacement_cost")
    private Integer replacementCost;


    @Column(name="rating")
    private String rating;

    @Column(name="special_features")
    private String specialFeatures;


    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "film_actor",
            joinColumns = { @JoinColumn(name = "film_id", referencedColumnName = "film_id") },
            inverseJoinColumns = { @JoinColumn(name = "actor_id", referencedColumnName =  "actor_id") }
    )
    // @JsonBackReference
    // @JsonIgnore
    private Set<Actor> actors = new HashSet<>();

    public Film(){}

    public Film(FilmDTO filmDto)
    {
        this.UpdateFilm(filmDto);
    }

    public void UpdateFilm(FilmDTO filmDto)
    {
        if(null != filmDto.getFilmId())             this.filmId = filmDto.getFilmId();
        if(null != filmDto.getTitle())              this.title = filmDto.getTitle();
        if(null != filmDto.getDescription())        this.description = filmDto.getDescription();
        if(null != filmDto.getLanguageId())         this.languageId = filmDto.getLanguageId();
        if(null != filmDto.getOriginalLanguageId()) this.originalLanguageId = filmDto.getOriginalLanguageId();
        if(null != filmDto.getRentalRate())         this.rentalDuration = filmDto.getRentalDuration();
        if(null != filmDto.getRentalDuration())     this.rentalRate = filmDto.getRentalRate();
        if(null != filmDto.getLength())             this.length = filmDto.getLength();
        if(null != filmDto.getReplacementCost())    this.replacementCost = filmDto.getReplacementCost();
        if(null != filmDto.getRating())             this.rating = filmDto.getRating();
        if(null != filmDto.getSpecialFeatures())    this.specialFeatures = filmDto.getSpecialFeatures();

    }


    public Integer getFilmId() {
        return filmId;
    }

    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getLanguageId() {
        return languageId;
    }

    public void setLanguageId(Integer languageId) {
        this.languageId = languageId;
    }

    public Integer getOriginalLanguageId() {
        return originalLanguageId;
    }

    public void setOriginalLanguageId(Integer originalLanguageId) {
        this.originalLanguageId = originalLanguageId;
    }

    public Integer getRentalDuration() {
        return rentalDuration;
    }

    public void setRentalDuration(Integer rentalDuration) {
        this.rentalDuration = rentalDuration;
    }

    public Double getRentalRate() {
        return rentalRate;
    }

    public void setRentalRate(Double rentalRate) {
        this.rentalRate = rentalRate;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public Integer getReplacementCost() {
        return replacementCost;
    }

    public void setReplacementCost(Integer replacementCost) {
        this.replacementCost = replacementCost;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getSpecialFeatures() {
        return specialFeatures;
    }

    public void setSpecialFeatures(String specialFeatures) {
        this.specialFeatures = specialFeatures;
    }

    public Set<Actor> getActors() {
        return actors;
    }

    public void setActors(Set<Actor> actors) {
        this.actors = actors;
    }
}
