package com.example.RESTSpring.Film;

import com.example.RESTSpring.Actor.ActorRepository;
import com.example.RESTSpring.FilmActor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

@Service
public class FilmService {
    @Autowired
    private FilmRepository filmRepository;
    @Autowired
    private ActorRepository actorRepository;
    @Autowired
    private FilmActorRepository filmActorRepository;

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
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Film not found with given ID"));
    }

    public Film AddFilm(Film newFilm)
    {
        return this.filmRepository.save(newFilm);
    }

    public Film UpdateFilm(Integer filmId, FilmDTO filmDTO)
    {
        Film film = GetFilm(filmId);
        film.UpdateFilm(filmDTO);

        System.out.println(filmDTO.getActorIds());

        if(filmDTO.getActorIds() != null) UpdateFilmActor(filmId, filmDTO.getActorIds());

        filmRepository.save(film);
        return film;
    }

    public Film DeleteFilm(Integer filmId)
    {
        Film film = GetFilm(filmId);

        if(film == null) return null;

        this.filmRepository.deleteById(filmId);

        return film;

    }



    public void UpdateFilmActor(Integer filmId, Set<Integer> actorIds)
    {
        // Delete all FilmActors with filmId
        Set<FilmActor> byFilmActorKeyFilmId = filmActorRepository.findByFilmActorKeyFilmId(filmId);
        List<FilmActor> filmActors = filmActorRepository.findAll();
        for(FilmActor filmActor : filmActors)
        {
            if(filmId.equals(filmActor.getFilmActorKey().getFilmId())) filmActorRepository.delete(filmActor);
        }

        System.out.println(actorIds.size());

        // Add FilmActors with new actorIds
        for(Integer actorId : actorIds)
        {
            actorRepository
                    .findById(actorId)
                    .ifPresent(filmActor -> filmActorRepository.save(new FilmActor(filmId, actorId)));

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


