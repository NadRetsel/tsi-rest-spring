package com.example.RESTSpring.FilmActor;

import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class FilmActorKey implements Serializable {

    @Column(name="film_id", nullable = false)
    private Integer filmId;

    @Column(name="actor_id", nullable = false)
    private Integer actorId;


    public FilmActorKey(){}

    public FilmActorKey(Integer filmId, Integer actorId) {
        this.filmId = filmId;
        this.actorId = actorId;
    }


    public Integer getFilmId() {
        return filmId;
    }

    public Integer getActorId() {
        return actorId;
    }


    public void setFilmId(Integer filmId) {
        this.filmId = filmId;
    }

    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }
}
