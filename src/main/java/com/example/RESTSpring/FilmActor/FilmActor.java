package com.example.RESTSpring.FilmActor;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;


@Entity
@Table(name="film_actor")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class FilmActor {

    @EmbeddedId
    private FilmActorKey filmActorKey;

    public FilmActor(){}

    public FilmActor(Integer filmId, Integer actorId)
    {
        this.filmActorKey = new FilmActorKey(filmId, actorId);
    }

    public FilmActorKey getFilmActorKey() {
        return filmActorKey;
    }

    public void setFilmActorKey(FilmActorKey filmActorKey) {
        this.filmActorKey = filmActorKey;
    }
}
