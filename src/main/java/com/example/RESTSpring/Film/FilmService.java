package com.example.RESTSpring.Film;

import com.example.RESTSpring.Actor.Actor;
import com.example.RESTSpring.Actor.ActorRepository;
import com.example.RESTSpring.Film.Film;
import com.example.RESTSpring.Film.FilmRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Service
public class FilmService {
    @Autowired
    private FilmRepository film_repository;
    @Autowired
    private ActorRepository actor_repository;

    public FilmService(FilmRepository film_repository, ActorRepository actor_repository)
    {
        this.film_repository = film_repository;
        this.actor_repository = actor_repository;
    }

    public Iterable<Film> GetAllFilms()
    {
        return this.film_repository.findAll();
    }

    public Film GetFilm(Integer film_id)
    {
        return this.film_repository.findById(film_id).orElse(null);
		/*
		return actor_repo
				.findById(actor_id)
				.orElseThrow(() -> new ResourceAccessException("Actor not found"));
		 */
    }

    public Film AddFilm(Film new_film)
    {
        return this.film_repository.save(new_film);
    }

    public Film DeleteFilm(Integer film_id)
    {
        Film film = GetFilm(film_id);

        if(film == null) return null;

        this.film_repository.deleteById(film_id);

        return film;

    }

    public Film AddActorToFilm(Map<String, Integer> film_actor_request)
    {
        Film film = this.film_repository.findById(film_actor_request.get("film_id")).orElse(null);
        Actor actor = this.actor_repository.findById(film_actor_request.get("actor_id")).orElse(null);

        if(film == null || actor == null) return null;

        System.out.println(film + " " + actor);

        Set<Actor> all_actors = film.getActors();
        all_actors.add(actor);
        film.setActors(all_actors);
        this.film_repository.save(film);

        return film;
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
    public Iterable<Set<Actor>> AllFilmActors()
    {
        List<Set<Actor>> all_actors = new LinkedList<>();
        for(Film film : this.film_repository.findAll())
        {
            all_actors.add(film.getActors());
        }

        return all_actors;
    }
}


