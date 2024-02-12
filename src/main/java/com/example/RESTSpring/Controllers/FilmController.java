package com.example.RESTSpring.Controllers;

import com.example.RESTSpring.Models.Actor;
import com.example.RESTSpring.Models.ActorRepository;
import com.example.RESTSpring.Models.Film;
import com.example.RESTSpring.Models.FilmRepository;
import com.example.RESTSpring.Services.*;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@RestController
@RequestMapping("/film")
public class FilmController {

    private FilmRepository film_repo;
    private ActorRepository actor_repo;

    public FilmController(ActorRepository actor_repo, FilmRepository film_repo) {
        this.actor_repo = actor_repo;
        this.film_repo = film_repo;
    }

    @GetMapping("/all")
    public Iterable<Film> GetAllFilms()
    {
        return FilmService.GetAllFilms(this.film_repo);
    }

    @GetMapping("/get/{id}")
    public Film GetFilm(@PathVariable("id") Integer film_id)
    {
        return  FilmService.GetFilm(this.film_repo, film_id);
		/*
		return actor_repo
				.findById(actor_id)
				.orElseThrow(() -> new ResourceAccessException("Actor not found"));
		 */
    }

    @PostMapping("/add")
    public Film AddFilm(@RequestBody Film new_film)
    {
        return FilmService.AddFilm(this.film_repo, new_film);
    }

    @DeleteMapping("/delete/{id}")
    public Film DeleteFilm(@PathVariable("id") Integer film_id)
    {
        return FilmService.DeleteFilm(this.film_repo, film_id);

    }

    @PutMapping("/addActor")
    public Film AddActorToFilm(@RequestBody Map<String,Integer> film_actor_request)
    {
        Integer id = film_actor_request.get("film_id");
        this.film_repo = FilmService.AddActorToFilm(this.film_repo, this.actor_repo, film_actor_request);
        return FilmService.GetFilm(this.film_repo, id);
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
        return FilmService.AllFilmActors(this.film_repo);
    }



}
