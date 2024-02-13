package com.example.RESTSpring.FilmActor;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@Table(name="film_actor")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "film_actor_id")
public class FilmActor {

    @EmbeddedId
    private FilmActorEntry film_actor_entry;

    public FilmActor(Integer film_id, Integer actor_id)
    {
        this.film_actor_entry = new FilmActorEntry(film_id, actor_id);
    }

    public FilmActorEntry GetFilm_actor_entry()
    {
        return film_actor_entry;
    }

    public void SetFilm_actor_entry(FilmActorEntry film_actor_entry)
    {
        this.film_actor_entry = film_actor_entry;
    }
}
