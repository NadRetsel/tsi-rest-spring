package com.example.RESTSpring.FilmActor;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;

@Entity
@Table(name="film_actor")
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
