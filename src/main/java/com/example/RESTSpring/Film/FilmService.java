package com.example.RESTSpring.Film;

import com.example.RESTSpring.Actor.Actor;
import com.example.RESTSpring.Actor.ActorRepository;
import com.example.RESTSpring.Film.Film;
import com.example.RESTSpring.Film.FilmRepository;
import com.example.RESTSpring.FilmActor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class FilmService {
    @Autowired
    private FilmRepository film_repository;
    @Autowired
    private ActorRepository actor_repository;
    @Autowired
    private FilmActorRepository film_actor_repository;

    public FilmService(FilmRepository film_repository, ActorRepository actor_repository, FilmActorRepository film_actor_repository)
    {
        this.film_repository = film_repository;
        this.actor_repository = actor_repository;
        this.film_actor_repository =  film_actor_repository;
    }

    public Iterable<Film> GetAllFilms()
    {
        return this.film_repository.findAll();
    }

    public Film GetFilm(Integer film_id)
    {
        return this.film_repository
                .findById(film_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Film not found with given ID"));
    }

    public Film AddFilm(Film new_film)
    {
        return this.film_repository.save(new_film);
    }

    public Film UpdateFilm(Integer film_id, FilmDTO film_dto)
    {
        Film film = GetFilm(film_id);
        film.UpdateFilm(film_dto);

        if(film_dto.getActor_ids() != null) UpdateFilmActor(film_id, film_dto.getActor_ids());

        film_repository.save(film);
        return film;
    }

    public Film DeleteFilm(Integer film_id)
    {
        Film film = GetFilm(film_id);

        if(film == null) return null;

        this.film_repository.deleteById(film_id);

        return film;

    }



    public void UpdateFilmActor(Integer film_id, Set<Integer> actor_ids)
    {
        // Delete all FilmActors with film_id
        List<FilmActor> film_actors = film_actor_repository.findAll();
        for(FilmActor film_actor : film_actors)
        {
            if(film_id.equals(film_actor.getFilm_actor_key().getFilm_id())) film_actor_repository.delete(film_actor);
        }

        // Add FilmActors with new actor_ids
        for(Integer actor_id : actor_ids)
        {
            actor_repository
                    .findById(actor_id)
                    .ifPresent(film_actor -> film_actor_repository.save(new FilmActor(film_id, actor_id)));

        }



    }



    /*
    public Film AddActorToFilm(Map<String, Integer> film_actor_request)
    {
        Film film = this.film_repository.findById(film_actor_request.get("film_id")).orElse(null);
        Actor actor = this.actor_repository.findById(film_actor_request.get("actor_id")).orElse(null);

        if(film == null || actor == null) return null;

        System.out.println(film + " " + actor);

        Set<Actor> all_actors = film.getActors();
        all_actors.add(actor);
        film.setActors(all_actors);
        this.film_repository.save(film);

        return film;
    }




    // ===== FILMS + ACTORS
    public Iterable<Set<Actor>> AllFilmActors()
    {
        List<Set<Actor>> all_actors = new LinkedList<>();
        for(Film film : this.film_repository.findAll())
        {
            all_actors.add(film.getActors());
        }

        return all_actors;
    }
    */
}


