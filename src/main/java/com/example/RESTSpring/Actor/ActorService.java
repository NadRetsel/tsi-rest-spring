package com.example.RESTSpring.Actor;

import com.example.RESTSpring.Film.Film;
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
    private final ActorRepository actor_repository;
    public ActorService (ActorRepository actor_repository) {
        this.actor_repository = actor_repository;
    }

    // Get all Actors from the repository
    public Iterable<Actor> GetAllActors()
    {
        return this.actor_repository.findAll();
    }

    // Get Actor by given ID
    public Actor GetActorByID(Integer actor_id)
    {
        return this.actor_repository
                .findById(actor_id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor not found with given ID"));
    }

    // Get Actor by given name
    public List<Actor> GetActorByName(ActorDTO actor_input) {
        String actor_input_first_name = actor_input.getFirst_name();
        if(actor_input_first_name != null) actor_input_first_name = actor_input_first_name.toUpperCase();

        String actor_input_last_name = actor_input.getLast_name();
        if(actor_input_last_name != null) actor_input_last_name = actor_input_last_name.toUpperCase();

        // Find all actors in table with matching names
        List<Actor> matching_actors = new LinkedList<>();
        for (Actor actor : this.actor_repository.findAll()) {
            String actor_first_name = actor.getFirst_name();
            String actor_last_name = actor.getLast_name();

            // Accept matching last name if first name is NULL
            if (actor_input_first_name == null){
                if(actor_last_name.equals(actor_input_last_name)){
                    matching_actors.add(actor);
                    continue;
                }
            }

            // Accept matching first name if last name is NULL
            if (actor_input_last_name == null){
                if(actor_first_name.equals(actor_input_first_name)){
                    matching_actors.add(actor);
                    continue;
                }
            }

            // Accept matching first name and last name
            if (actor_first_name.equals(actor_input_first_name) && actor_last_name.equals(actor_input_last_name))
            {
                matching_actors.add(actor);
            }

        }

        if(matching_actors.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor not found with given name");

        return matching_actors;
    }

    public Actor AddActor(ActorDTO actor_dto)
    {
        return this.actor_repository.save(new Actor(actor_dto));
    }


    public Actor UpdateActor(Integer actor_id, ActorDTO actor_dto)
    {
        Actor actor = GetActorByID(actor_id);
        actor.UpdateActor(actor_dto);
        this.actor_repository.save(actor);

        return actor;
    }

    public Actor DeleteActor(Integer actor_id)
    {
        Actor actor = GetActorByID(actor_id);
        this.actor_repository.deleteById(actor_id);

        return actor;
    }



    // ===== ACTORS + FILM =====
    public Iterable<Set<Film>> ActorAllFilms()
    {
        List<Set<Film>> all_films = new LinkedList<>();
        for(Actor actor : this.actor_repository.findAll())
        {
            all_films.add(actor.getFilms());
        }

        System.out.println(all_films.size());
        return all_films;
    }


    public Set<Film> ActorAllFilmsByID(Integer actor_id)
    {
        Actor actor = GetActorByID(actor_id);

        return actor.getFilms();
    }

    public List<Set<Film>> ActorAllFilmsByName(ActorDTO actor_dto)
    {
        List<Actor> matching_actors = GetActorByName(actor_dto);

        // Get all films by every actor of matching name
        List<Set<Film>> all_films_by_actor = new LinkedList<>();
        for(Actor actor : matching_actors)
        {
            all_films_by_actor.add(actor.getFilms());
        }
        return all_films_by_actor;
    }
}

