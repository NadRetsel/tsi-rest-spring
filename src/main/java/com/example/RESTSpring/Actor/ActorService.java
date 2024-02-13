package com.example.RESTSpring.Actor;

import com.example.RESTSpring.Film.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public Iterable<Actor> GetAllActors()
    {
        return this.actor_repository.findAll();
    }

    public Actor GetActorByID(Integer actor_id)
    {
        return this.actor_repository.findById(actor_id).orElse(null);
    }

    public List<Actor> GetActorByName(Actor actor_input) {
        String actor_input_first_name = actor_input.getFirst_name();
        String actor_input_last_name = actor_input.getLast_name();

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

        return matching_actors;
    }

    public Actor AddNewActor(Actor new_actor)
    {
        return this.actor_repository.save(new_actor);
    }

    public Actor DeleteActor(Integer actor_id)
    {
        Actor actor = GetActorByID(actor_id);

        if(actor == null) return null;

        this.actor_repository.deleteById(actor_id);

        return actor;

    }

    public Actor UpdateActor(Integer old_id, Actor new_actor)
    {
        Actor old_actor = GetActorByID(old_id);

        if(old_actor == null) return null;

        old_actor.setFirst_name(new_actor.getFirst_name());
        old_actor.setLast_name(new_actor.getLast_name());
        this.actor_repository.save(old_actor);

        return old_actor;
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
        Actor actor = this.actor_repository.findById(actor_id).orElse(null);
        if(actor == null) return null;

        return actor.getFilms();
    }

    public List<Set<Film>> ActorAllFilmsByName(Actor actor_input)
    {
        List<Actor> matching_actors = GetActorByName(actor_input);

        // Get all films by every actor of matching name
        List<Set<Film>> all_films_by_actor = new LinkedList<>();
        for(Actor actor : matching_actors)
        {
            all_films_by_actor.add(actor.getFilms());
        }
        return all_films_by_actor;
    }
}

