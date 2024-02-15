package com.example.RESTSpring.Actor;

import com.example.RESTSpring.Film.*;
import com.example.RESTSpring.FilmActor.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@Service
public class ActorService {

    @Autowired
    private final ActorRepository actorRepository;
    @Autowired
    private final FilmRepository filmRepository;
    @Autowired
    private final FilmActorRepository filmActorRepository;


    public ActorService (ActorRepository actorRepository, FilmRepository filmRepository, FilmActorRepository filmActorRepository) {
        this.actorRepository = actorRepository;
        this.filmRepository = filmRepository;
        this.filmActorRepository = filmActorRepository;
    }

    // ===== Core functions: GET, POST, PATCH, DELETE =====
    /**
     * Get all Actors from the repository.
     *
     * @return List of all Actors from ActorRepository.
     */
    public Iterable<Actor> GetAllActors() {
        return this.actorRepository.findAll();
    }

    /**
     * Find Actor with matching ID.
     *
     * @param actorId - ID of Actor to be found.
     *
     * @return Actor with matching ID - or throw NOT_FOUND if no Actors found.
     */
    public Actor GetActorByID(Integer actorId) {
        return this.actorRepository
                .findById(actorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor cannot be found with given ID"));
    }

    /**
     * Find Actor with matching name.
     * <br>
     * <br>If firstName is null, match with only lastName.
     * <br>If lastName is null, match with only firstName.
     * <br>Otherwise, match with full name.
     *
     * @param actorDTO - Actor Data Transfer Object that holds the firstName and lastName to be searched.
     *
     * @return Set of Actors with matching name(s).
     */
    public Set<Actor> GetActorByName(ActorDTO actorDTO) {
        return this.actorRepository.getByName(actorDTO.getFirstName(), actorDTO.getLastName());
    }

    /**
     * Add new Actor to the repository.
     *
     * @param actorDTO - Actor DTO that holds the new Actor's data.
     *
     * @return The newly created and added Actor.
     */
    public Actor AddActor(ActorDTO actorDTO) {
        return this.actorRepository.save(new Actor(actorDTO));
    }

    /**
     * Update an existing Actor's fields with new data.
     *
     * @param actorId - The ID of the Actor to be updated.
     * @param actorDTO - Holds the new data to insert into the given Actor.
     *
     * @return The updated Actor.
     */
    public Actor UpdateActor(Integer actorId, ActorDTO actorDTO) {
        Actor actor = GetActorByID(actorId);
        actor.updateActor(actorDTO);

        this.actorRepository.save(actor);

        if(actorDTO.getFilmIds() != null) this.UpdateFilmActors(actorId, actorDTO.getFilmIds());

        return actor;
    }

    /**
     * Attempts to delete the Actor with matching ID from the database.
     * <br>
     * <br>NOTE: Database constraints will override and prevent the deletion.
     *
     * @param actorId - ID of the Actor to be deleted.
     *
     * @return Actor that was attempted to be deleted.
     */
    // TODO Catch constraint violations when attempting to delete
    public Actor DeleteActor(Integer actorId) {
        Actor actor = GetActorByID(actorId);

        this.actorRepository.deleteById(actorId);

        return actor;
    }


    /**
     * Updates the FilmActor table to replace all of Actor's old Films with the new Films.
     *
     * @param actorId - ID of Actor that was updated.
     * @param filmIds - List of new Films's IDs associated with the Actor.
     */
    public void UpdateFilmActors(Integer actorId, Set<Integer> filmIds) {
        // Delete all existing FilmActors with actorId
        this.filmActorRepository.deleteByFilmActorKeyActorId(actorId);

        // Add FilmActors with new filmIds that have existing Films
        List<Film> films = this.filmRepository.findAllById(filmIds);
        for(Film film : films) this.filmActorRepository.save(new FilmActor(film.getFilmId(), actorId));
    }


    // ===== Extra functionality =====

    public Iterable<Set<Film>> ActorAllFilms() {
        List<Set<Film>> allFilms = new LinkedList<>();
        for(Actor actor : this.actorRepository.findAll())
        {
            allFilms.add(actor.getFilms());
        }

        System.out.println(allFilms.size());
        return allFilms;
    }


    public Set<Film> ActorAllFilmsByID(Integer actorId) {
        Actor actor = GetActorByID(actorId);

        return actor.getFilms();
    }

    public List<Set<Film>> ActorAllFilmsByName(ActorDTO actorDTO) {
        Set<Actor> matchingActors = GetActorByName(actorDTO);

        // Get all films by every actor of matching name
        List<Set<Film>> allFilmsByActor = new LinkedList<>();
        for(Actor actor : matchingActors)
        {
            allFilmsByActor.add(actor.getFilms());
        }
        return allFilmsByActor;
    }

}

