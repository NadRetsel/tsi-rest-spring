package com.example.RESTSpring.Actor;

import com.example.RESTSpring.Film.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/actors")
public class ActorController {

    @Autowired
    private final ActorService actor_service;

    public ActorController(ActorService actor_service) {
        this.actor_service = actor_service;
    }

    // ========== ACTORS ==========
    @GetMapping("")
    public Iterable<Actor> GetAllActors()
    {
        return this.actor_service.GetAllActors();
    }

    @GetMapping("/{id}")
    public Actor GetActorByID(@PathVariable("id") Integer actor_id)
    {
        return this.actor_service.GetActorByID(actor_id);
    }

    @GetMapping("/getByName")
    public List<Actor> GetActorByName(@RequestBody ActorDTO actor_input) {

        return this.actor_service.GetActorByName(actor_input);
    }

    @PostMapping("")
    public Actor AddActor(@RequestBody ActorDTO new_actor)
    {
        return this.actor_service.AddActor(new_actor);
    }

    @DeleteMapping("/{id}")
    public Actor DeleteActor(@PathVariable("id") Integer actor_id)
    {
        return this.actor_service.DeleteActor(actor_id);

    }

    @PutMapping("/{id}")
    public Actor UpdateActor(@PathVariable("id") Integer old_id, @RequestBody ActorDTO new_actor)
    {
        return this.actor_service.UpdateActor(old_id, new_actor);
    }




    // ===== FILM + ACTOR =====
    @GetMapping("/allFilms")
    public Iterable<Set<Film>> ActorAllFilms()
    {
        return this.actor_service.ActorAllFilms();
    }



    @GetMapping("/{id}/allFilms")
    public Set<Film> ActorAllFilmsByID(@PathVariable("id") Integer actor_id)
    {
        return this.actor_service.ActorAllFilmsByID(actor_id);
    }

    @GetMapping("/allFilmsByName")
    public List<Set<Film>> ActorAllFilmsByName(@RequestBody ActorDTO actor_input)
    {
        return this.actor_service.ActorAllFilmsByName(actor_input);
    }
}
