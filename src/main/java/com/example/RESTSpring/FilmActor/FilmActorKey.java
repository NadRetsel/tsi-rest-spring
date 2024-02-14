package com.example.RESTSpring.FilmActor;

import jakarta.persistence.*;

import java.io.Serializable;

@Embeddable
public class FilmActorKey implements Serializable {
    @Column(name="film_id", nullable = false)
    private Integer film_id;

    @Column(name="actor_id", nullable = false)
    private Integer actor_id;

    public FilmActorKey(){}
    public FilmActorKey(Integer film_id, Integer actor_id)
    {
        this.film_id = film_id;
        this.actor_id = actor_id;
    }

    public Integer getFilm_id() {
        return film_id;
    }

    public void setFilm_id(Integer film_id) {
        this.film_id = film_id;
    }

    public Integer getActor_id() {
        return actor_id;
    }

    public void setActor_id(Integer actor_id) {
        this.actor_id = actor_id;
    }
}
