package com.example.RESTSpring.FilmActor;

import jakarta.persistence.*;

@Embeddable
public class FilmActorEntry {
    @Column(name="film_id", nullable = false)
    private Integer film_id;

    @Column(name="actor_id", nullable = false)
    private Integer actor_id;

    public FilmActorEntry(Integer film_id, Integer actor_id)
    {
        this.film_id = film_id;
        this.actor_id = actor_id;
    }

    public Integer GetFilm_ID() {
        return film_id;
    }

    public void SetFilm_ID(Integer film_id) {
        this.film_id = film_id;
    }

    public Integer GetActor_ID() {
        return actor_id;
    }

    public void SetActor_ID(Integer actor_id) {
        this.actor_id = actor_id;
    }



}
