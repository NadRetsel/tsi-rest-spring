package com.example.RESTSpring.Controllers;

import com.example.RESTSpring.*;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;

@RestController
@RequestMapping("/actor")
public class ActorController {

    private ActorRepository actor_repo;

    public ActorController(ActorRepository actor_repo) {
        this.actor_repo = actor_repo;
    }

    // ========== ACTORS ==========
    @GetMapping("/getAll")
    public Iterable<Actor> GetAllActors()
    {
        return actor_repo.findAll();
    }

    @GetMapping("/getByID/{id}")
    public Actor getActorByID(@PathVariable("id") Integer actor_id)
    {
        return actor_repo.findById(actor_id).orElse(null);
		/*
		return actor_repo
				.findById(actor_id)
				.orElseThrow(() -> new ResourceAccessException("Actor not found"));
		 */
    }

    @GetMapping("/getByName")
    public List<Actor> GetActorByName(@RequestBody Actor actor_input) {
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

    @PostMapping("/add")
    public Actor AddActor(@RequestBody Actor new_actor)
    {
        return actor_repo.save(new_actor);
    }

    @DeleteMapping("/delete/{id}")
    public Actor DeleteActor(@PathVariable("id") Integer actor_id)
    {
        Actor actor = getActorByID(actor_id);

        if(actor == null) return null;

        actor_repo.deleteById(actor_id);

        return actor;

    }

    @PutMapping("/update/{id}")
    public Actor UpdateActor(@PathVariable("id") Integer old_id, @RequestBody Actor new_actor)
    {
        Actor old_actor = getActorByID(old_id);

        if(old_actor == null) return null;

        old_actor.setFirst_name(new_actor.getFirst_name());
        old_actor.setLast_name(new_actor.getLast_name());
        actor_repo.save(old_actor);

        return old_actor;
    }
}
