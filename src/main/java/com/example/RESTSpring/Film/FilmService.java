package com.example.RESTSpring.Services;

import com.example.RESTSpring.Models.Actor;
import com.example.RESTSpring.Models.ActorRepository;
import com.example.RESTSpring.Models.Film;
import com.example.RESTSpring.Models.FilmRepository;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class FilmService {

    public static Iterable<Film> GetAllFilms(FilmRepository film_repo)
    {
        return film_repo.findAll();
    }

    public static Film GetFilm(FilmRepository film_repo, Integer film_id)
    {
        return film_repo.findById(film_id).orElse(null);
		/*
		return actor_repo
				.findById(actor_id)
				.orElseThrow(() -> new ResourceAccessException("Actor not found"));
		 */
    }

    public static Film AddFilm(FilmRepository film_repo, Film new_film)
    {
        return film_repo.save(new_film);
    }

    public static Film DeleteFilm(FilmRepository film_repo, Integer film_id)
    {
        Film film = GetFilm(film_repo, film_id);

        if(film == null) return null;

        film_repo.deleteById(film_id);

        return film;

    }

    public static FilmRepository AddActorToFilm(FilmRepository film_repo, ActorRepository actor_repo, Map<String, Integer> film_actor_request)
    {
        System.out.println(film_repo);
        System.out.println(actor_repo);

        Film film = film_repo.findById(film_actor_request.get("film_id")).orElse(null);
        Actor actor = actor_repo.findById(film_actor_request.get("actor_id")).orElse(null);

        if(film == null || actor == null) return film_repo;

        System.out.println(film + " " + actor);

        Set<Actor> all_actors = film.getActors();
        all_actors.add(actor);
        film.setActors(all_actors);
        film_repo.save(film);

        return film_repo;
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
    public static Iterable<Set<Actor>> AllFilmActors(FilmRepository film_repo)
    {
        List<Set<Actor>> all_actors = new LinkedList<>();
        for(Film film : film_repo.findAll())
        {
            all_actors.add(film.getActors());
        }
        System.out.println(all_actors.size());

        return all_actors;
    }
}


