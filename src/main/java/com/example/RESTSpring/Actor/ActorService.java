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

    // Get all Actors from the repository
    public Iterable<Actor> GetAllActors()
    {
        return this.actorRepository.findAll();
    }

    // Get Actor by given ID
    public Actor GetActorByID(Integer actorId)
    {
        return this.actorRepository
                .findById(actorId)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor cannot be found with given ID"));
    }

    // Get Actor by given name
    public Set<Actor> GetActorByName(ActorDTO actorDTO)
    {
        String firstName = actorDTO.getFirstName();
        String lastName = actorDTO.getLastName();

        return this.actorRepository.getByName(firstName, lastName);

        /*
        // Find all actors in table with matching names
        List<Actor> matchingActors = new LinkedList<>();
        for (Actor actor : this.actorRepository.findAll()) {
            String actorFirstName = actor.getFirstName();
            String actorLastName = actor.getLastName();

            // Accept matching last name if first name is NULL
            if (actorInputFirstName == null){
                if(actorLastName.equals(actorInputLastName)){
                    matchingActors.add(actor);
                    continue;
                }
            }

            // Accept matching first name if last name is NULL
            if (actorInputLastName == null){
                if(actorFirstName.equals(actorInputFirstName)){
                    matchingActors.add(actor);
                    continue;
                }
            }

            // Accept matching first name and last name
            if (actorFirstName.equals(actorInputFirstName) && actorLastName.equals(actorInputLastName))
            {
                matchingActors.add(actor);
            }


        }



        if(matchingActors.isEmpty()) throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Actor(s) cannot be found with given name");

        return matchingActors;

         */
    }

    public Actor AddActor(ActorDTO actorDTO)
    {
        return this.actorRepository.save(new Actor(actorDTO));
    }

    public Actor UpdateActor(Integer actorId, ActorDTO actorDTO)
    {
        Actor actor = GetActorByID(actorId);
        actor.UpdateActor(actorDTO);

        this.actorRepository.save(actor);

        if(actorDTO.getFilmIds() != null) this.UpdateFilmActors(actorId, actorDTO.getFilmIds());

        return actor;
    }

    public Actor DeleteActor(Integer actorId)
    {
        Actor actor = GetActorByID(actorId);

        this.actorRepository.deleteById(actorId);

        return actor;
    }


    public void UpdateFilmActors(Integer actorId, Set<Integer> filmIds)
    {
        // Delete all existing FilmActors with actorId
        this.filmActorRepository.deleteByFilmActorKeyActorId(actorId);

        // Add FilmActors with new filmIds that have existing Films
        List<Film> films = this.filmRepository.findAllById(filmIds);
        for(Film film : films) this.filmActorRepository.save(new FilmActor(film.getFilmId(), actorId));
    }



    public Iterable<Set<Film>> ActorAllFilms()
    {
        List<Set<Film>> allFilms = new LinkedList<>();
        for(Actor actor : this.actorRepository.findAll())
        {
            allFilms.add(actor.getFilms());
        }

        System.out.println(allFilms.size());
        return allFilms;
    }


    public Set<Film> ActorAllFilmsByID(Integer actorId)
    {
        Actor actor = GetActorByID(actorId);

        return actor.getFilms();
    }

    public List<Set<Film>> ActorAllFilmsByName(ActorDTO actorDTO)
    {
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

