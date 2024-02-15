package com.example.RESTSpring.Film;

import com.example.RESTSpring.Actor.*;
import com.example.RESTSpring.FilmActor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class FilmService {
    @Autowired
    private final FilmRepository filmRepository;
    @Autowired
    private final ActorRepository actorRepository;
    @Autowired
    private final FilmActorRepository filmActorRepository;

    public FilmService(FilmRepository filmRepository, ActorRepository actorRepository, FilmActorRepository filmActorRepository) {
        this.filmRepository = filmRepository;
        this.actorRepository = actorRepository;
        this.filmActorRepository = filmActorRepository;
    }

    public Iterable<Film> GetAllFilms()
    {
        return this.filmRepository.findAll();
    }

    public Film GetFilm(Integer filmId) {
        return this.filmRepository
                .findById(filmId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Film cannot be found with given ID"));
    }

    public Film AddFilm(FilmDTO filmDTO)
    {
        Film newFilm = new Film(filmDTO);
        return this.filmRepository.save(newFilm);
    }


    public Film UpdateFilm(Integer filmId, FilmDTO filmDTO)
    {
        Film film = GetFilm(filmId);
        film.updateFilm(filmDTO);

        if(filmDTO.getActorIds() != null) UpdateFilmActor(filmId, filmDTO.getActorIds());

        filmRepository.save(film);
        return film;
    }

    public Film DeleteFilm(Integer filmId)
    {
        Film film = GetFilm(filmId);

        this.filmRepository.deleteById(filmId);

        return film;

    }



    public void UpdateFilmActor(Integer filmId, Set<Integer> actorIds)
    {
        // Delete all existing FilmActors with filmId
        filmActorRepository.deleteByFilmActorKeyFilmId(filmId);

        // Add FilmActors with new actorIds that have existing Actors
        List<Actor> actors = actorRepository.findAllById(actorIds);
        for(Actor actor : actors) filmActorRepository.save(new FilmActor(filmId, actor.getActorId()));
    }

}


