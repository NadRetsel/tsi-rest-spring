package com.example.RESTSpring.Controllers;

import com.example.RESTSpring.Models.Actor;
import com.example.RESTSpring.Models.ActorRepository;
import com.example.RESTSpring.Models.Film;
import org.springframework.web.bind.annotation.*;

import com.example.RESTSpring.Services.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/actors")
public class ActorController {

    private ActorRepository actor_repo;

    public ActorController(ActorRepository actor_repo) {
        this.actor_repo = actor_repo;
    }

    // ========== ACTORS ==========
    @GetMapping("/all")
    public Iterable<Actor> GetAllActors()
    {
        return ActorService.GetAllActors(this.actor_repo);
    }

    @GetMapping("/getByID/{id}")
    public Actor getActorByID(@PathVariable("id") Integer actor_id)
    {
        return ActorService.GetActorByID(this.actor_repo, actor_id);
		/*
		return actor_repo
				.findById(actor_id)
				.orElseThrow(() -> new ResourceAccessException("Actor not found"));
		 */
    }

    @GetMapping("/getByName")
    public List<Actor> GetActorByName(@RequestBody Actor actor_input) {

        return ActorService.GetActorByName(this.actor_repo, actor_input);
    }

    @PostMapping("/add")
    public Actor AddActor(@RequestBody Actor new_actor)
    {
        return ActorService.AddNewActor(this.actor_repo, new_actor);
    }

    @DeleteMapping("/delete/{id}")
    public Actor DeleteActor(@PathVariable("id") Integer actor_id)
    {
        return ActorService.DeleteActor(this.actor_repo, actor_id);

    }

    @PutMapping("/update/{id}")
    public Actor UpdateActor(@PathVariable("id") Integer old_id, @RequestBody Actor new_actor)
    {
        return ActorService.UpdateActor(this.actor_repo, old_id, new_actor);
    }




    // ===== FILM + ACTOR =====
    @GetMapping("/allFilms")
    public Iterable<Set<Film>> ActorAllFilms()
    {
        return ActorService.ActorAllFilms(this.actor_repo);
    }


    @GetMapping("/allFilmsByID/{id}")
    public Set<Film> ActorAllFilmsByID(@PathVariable("id") Integer actor_id)
    {
        return ActorService.ActorAllFilmsByID(this.actor_repo, actor_id);
    }

    @GetMapping("/allFilmsByName")
    public List<Set<Film>> ActorAllFilmsByName(@RequestBody Actor actor_input)
    {
        return ActorService.ActorAllFilmsByName(this.actor_repo, actor_input);
    }
}
