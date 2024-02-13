package com.example.RESTSpring.Film;

import com.example.RESTSpring.Actor.Actor;
import com.example.RESTSpring.Actor.ActorRepository;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/films")
public class FilmController {

    private FilmService film_service;

    public FilmController(FilmService film_service) {
        this.film_service = film_service;
    }

    @GetMapping("")
    public Iterable<Film> GetAllFilms()
    {
        return this.film_service.GetAllFilms();
    }

    @GetMapping("/{id}")
    public Film GetFilm(@PathVariable("id") Integer film_id)
    {
        return  this.film_service.GetFilm(film_id);
		/*
		return actor_repo
				.findById(actor_id)
				.orElseThrow(() -> new ResourceAccessException("Actor not found"));
		 */
    }

    @PostMapping("")
    public Film AddFilm(@RequestBody Film new_film)
    {
        return this.film_service.AddFilm(new_film);
    }

    @DeleteMapping("/{id}")
    public Film DeleteFilm(@PathVariable("id") Integer film_id)
    {
        return this.film_service.DeleteFilm(film_id);

    }

    @PutMapping("/addActor")
    public Film AddActorToFilm(@RequestBody Map<String,Integer> film_actor_request)
    {
        Integer id = film_actor_request.get("film_id");
        return this.film_service.AddActorToFilm(film_actor_request);
    }

	/*
	@PutMapping("/updateFilm/{id}")
	public Film UpdateFilm(@PathVariable("id") Integer old_id, @RequestBody Film new_film)
	{
		Film old_film = GetFilm(old_id);

		if(old_film == null) return null;

		old_film.set (new_film.get ());
		old_film.set (new_film.get ());
		film_repo.save(new_film);

		return new_film;
	}
	*/


    // ===== FILMS + ACTORS
    @GetMapping("/allFilmActors")
    public Iterable<Set<Actor>> AllFilmActors()
    {
        return this.film_service.AllFilmActors();
    }



}
