package com.example.RESTSpring.Actor;

import com.example.RESTSpring.Film.Film;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/actors")
public class ActorController {

    @Autowired
    private final ActorService actorService;

    public ActorController(ActorService actorService) {
        this.actorService = actorService;
    }

    // ========== ACTORS ==========
    @GetMapping("")
    public Iterable<Actor> GetAllActors()
    {
        return this.actorService.GetAllActors();
    }

    @GetMapping("/{id}")
    public Actor GetActorByID(@PathVariable("id") Integer actorId)
    {
        return this.actorService.GetActorByID(actorId);
    }

    @GetMapping("/getByName")
    public Set<Actor> GetActorByName(@RequestBody ActorDTO actorInput) {

        return this.actorService.GetActorByName(actorInput);
    }

    @PostMapping("")
    public Actor AddActor(@RequestBody ActorDTO newActor)
    {
        return this.actorService.AddActor(newActor);
    }

    @DeleteMapping("/{id}")
    public Actor DeleteActor(@PathVariable("id") Integer actorId)
    {
        return this.actorService.DeleteActor(actorId);

    }

    @PatchMapping("/{id}")
    public Actor UpdateActor(@PathVariable("id") Integer oldId, @RequestBody ActorDTO newActor)
    {
        return this.actorService.UpdateActor(oldId, newActor);
    }




    // ===== FILM + ACTOR =====
    @GetMapping("/allFilms")
    public Iterable<Set<Film>> ActorAllFilms()
    {
        return this.actorService.ActorAllFilms();
    }



    @GetMapping("/{id}/allFilms")
    public Set<Film> ActorAllFilmsByID(@PathVariable("id") Integer actorId)
    {
        return this.actorService.ActorAllFilmsByID(actorId);
    }

    @GetMapping("/allFilmsByName")
    public List<Set<Film>> ActorAllFilmsByName(@RequestBody ActorDTO actorInput)
    {
        return this.actorService.ActorAllFilmsByName(actorInput);
    }

}
