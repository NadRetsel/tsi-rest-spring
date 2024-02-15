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

    // ===== Core functions: GET, POST, PATCH, DELETE =====
    /**
     * Get all Films from the repository
     *
     * @return List of all Films from FilmRepository
     */
    public Iterable<Film> GetAllFilms()
    {
        return this.filmRepository.findAll();
    }

    /**
     * Find Actor with matching ID
     *
     * @param filmId - ID of Film to be found
     *
     * @return Film with matching ID - or throw NOT_FOUND if no Films found
     */
    public Film GetFilm(Integer filmId) {
        return this.filmRepository
                .findById(filmId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Film cannot be found with given ID"));
    }

    /**
     * Add new Film to the repository
     *
     * @param filmDTO - Film DTO that holds the new Film's data
     *
     * @return The newly created and added Film
     */
    public Film AddFilm(FilmDTO filmDTO) {
        Film newFilm = new Film(filmDTO);
        return this.filmRepository.save(newFilm);
    }

    /**
     * Update an existing Film's fields with new data
     *
     * @param filmId - The ID of the Film to be updated
     * @param filmDTO - Holds the new data to insert into the given Film
     *
     * @return The updated Film
     */
    public Film UpdateFilm(Integer filmId, FilmDTO filmDTO) {
        Film film = GetFilm(filmId);
        film.updateFilm(filmDTO);

        if(filmDTO.getActorIds() != null) UpdateFilmActor(filmId, filmDTO.getActorIds());

        filmRepository.save(film);
        return film;
    }

    /**
     * Attempts to delete the Film with matching ID from the database
     *
     * NOTE: Database constraints will override and prevent the deletion
     *
     * @param filmId - ID of the Actor to be deleted
     *
     * @return Film that was attempted to be deleted
     */
    // TODO Catch constraint violations when attempting to delete
    public Film DeleteFilm(Integer filmId) {
        Film film = GetFilm(filmId);

        this.filmRepository.deleteById(filmId);

        return film;
    }


    // ===== Extra functionality =====
    /**
     * Updates the FilmActor table to replace all of Film's old Actors with the new Actors
     *
     * @param filmId - ID of Film that was updated
     * @param actorIds - List of new Actors's IDs associated with the Film
     */
    public void UpdateFilmActor(Integer filmId, Set<Integer> actorIds) {
        // Delete all existing FilmActors with filmId
        filmActorRepository.deleteByFilmActorKeyFilmId(filmId);

        // Add FilmActors with new actorIds that have existing Actors
        List<Actor> actors = actorRepository.findAllById(actorIds);
        for(Actor actor : actors) filmActorRepository.save(new FilmActor(filmId, actor.getActorId()));
    }

}


