package com.example.RESTSpring.FilmActor;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@Table(name="film_actor")
public class FilmActor {

    @EmbeddedId
    private FilmActorKey film_actor_key;

    public FilmActor(){}

    public FilmActor(Integer film_id, Integer actor_id)
    {
        this.film_actor_key = new FilmActorKey(film_id, actor_id);
    }

    public FilmActorKey getFilm_actor_key() {
        return film_actor_key;
    }

    public void setFilm_actor_key(FilmActorKey film_actor_key) {
        this.film_actor_key = film_actor_key;
    }
}
