package com.example.RESTSpring.Services;

import com.example.RESTSpring.Models.Actor;
import com.example.RESTSpring.Models.ActorRepository;
import com.example.RESTSpring.Models.Film;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

public class ActorService {

    public static Iterable<Actor> GetAllActors(ActorRepository actor_repo)
    {
        return actor_repo.findAll();
    }

    public ActorService()
    {

    }


    public static Actor GetActorByID(ActorRepository actor_repo, Integer actor_id)
    {
        return actor_repo.findById(actor_id).orElse(null);
    }

    public static List<Actor> GetActorByName(ActorRepository actor_repo, Actor actor_input) {
        String actor_input_first_name = actor_input.getFirst_name();
        String actor_input_last_name = actor_input.getLast_name();

        // Find all actors in table with matching names
        List<Actor> matching_actors = new LinkedList<>();
        for (Actor actor : actor_repo.findAll()) {
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

    public static Actor AddNewActor(ActorRepository actor_repo, Actor new_actor)
    {
        return actor_repo.save(new_actor);
    }

    public static Actor DeleteActor(ActorRepository actor_repo, Integer actor_id)
    {
        Actor actor = GetActorByID(actor_repo, actor_id);

        if(actor == null) return null;

        actor_repo.deleteById(actor_id);

        return actor;

    }

    public static Actor UpdateActor(ActorRepository actor_repo, Integer old_id, Actor new_actor)
    {
        System.out.println(actor_repo);
        Actor old_actor = GetActorByID(actor_repo, old_id);

        if(old_actor == null) return null;

        old_actor.setFirst_name(new_actor.getFirst_name());
        old_actor.setLast_name(new_actor.getLast_name());
        actor_repo.save(old_actor);

        return old_actor;
    }



    // ===== ACTORS + FILM =====
    public static Iterable<Set<Film>> ActorAllFilms(ActorRepository actor_repo)
    {
        List<Set<Film>> all_films = new LinkedList<>();
        for(Actor actor : actor_repo.findAll())
        {
            all_films.add(actor.getFilms());
        }

        System.out.println(all_films.size());
        return all_films;
    }


    public static Set<Film> ActorAllFilmsByID(ActorRepository actor_repo, Integer actor_id)
    {
        Actor actor = actor_repo.findById(actor_id).orElse(null);
        if(actor == null) return null;

        return actor.getFilms();
    }

    public static List<Set<Film>> ActorAllFilmsByName(ActorRepository actor_repo, Actor actor_input)
    {
        List<Actor> matching_actors = GetActorByName(actor_repo, actor_input);

        // Get all films by every actor of matching name
        List<Set<Film>> all_films_by_actor = new LinkedList<>();
        for(Actor actor : matching_actors)
        {
            all_films_by_actor.add(actor.getFilms());
        }
        return all_films_by_actor;
    }
}

