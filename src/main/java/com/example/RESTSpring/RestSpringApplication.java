package com.example.RESTSpring;
import com.example.RESTSpring.Actor.Actor;
import com.example.RESTSpring.Actor.ActorController;
import com.example.RESTSpring.Actor.ActorRepository;
import com.example.RESTSpring.City.City;
import com.example.RESTSpring.City.CityRepository;
import com.example.RESTSpring.Country.Country;
import com.example.RESTSpring.Country.CountryRepository;
import com.example.RESTSpring.Film.Film;
import com.example.RESTSpring.Film.FilmController;
import com.example.RESTSpring.Film.FilmRepository;
import org.springframework.web.bind.annotation.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
@RestController
@RequestMapping("/home")
@CrossOrigin
public class RestSpringApplication {

	public static ActorRepository actor_repo;
	private FilmRepository film_repo;
	private CountryRepository country_repo;
	private CityRepository city_repo;

	public RestSpringApplication(ActorRepository actor_repo, FilmRepository film_repo, CountryRepository country_repo, CityRepository city_repo) {
		this.actor_repo = actor_repo;
		this.film_repo = film_repo;
		this.country_repo = country_repo;
		this.city_repo = city_repo;

		new ActorController(actor_repo);
		new FilmController(actor_repo, film_repo);
	}

	public static void main(String[] args) {
		SpringApplication.run(RestSpringApplication.class, args);
	}


	// ========== ACTORS ==========
	@GetMapping("/allActors")
	public Iterable<Actor> GetAllActors()
	{
		return actor_repo.findAll();
	}

	@GetMapping("/getActorByID/{id}")
	public Actor getActorByID(@PathVariable("id") Integer actor_id)
	{
		return actor_repo.findById(actor_id).orElse(null);
		/*
		return actor_repo
				.findById(actor_id)
				.orElseThrow(() -> new ResourceAccessException("Actor not found"));
		 */
	}

	@GetMapping("/getActorByName")
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

	@PostMapping("/addActor")
	public Actor AddActor(@RequestBody Actor new_actor)
	{
		return actor_repo.save(new_actor);
	}

	@DeleteMapping("/deleteActor/{id}")
	public Actor DeleteActor(@PathVariable("id") Integer actor_id)
	{
		Actor actor = getActorByID(actor_id);

		if(actor == null) return null;

		actor_repo.deleteById(actor_id);

		return actor;

	}

	@PutMapping("/updateActor/{id}")
	public Actor UpdateActor(@PathVariable("id") Integer old_id, @RequestBody Actor new_actor)
	{
		Actor old_actor = getActorByID(old_id);

		if(old_actor == null) return null;

		old_actor.setFirst_name(new_actor.getFirst_name());
		old_actor.setLast_name(new_actor.getLast_name());
		actor_repo.save(old_actor);

		return old_actor;
	}




	// ========== FILMS ==========
	@GetMapping("/allFilms")
	public Iterable<Film> GetAllFilms()
	{
		return film_repo.findAll();
	}

	@GetMapping("/getFilm/{id}")
	public Film GetFilm(@PathVariable("id") Integer film_id)
	{
		return film_repo.findById(film_id).orElse(null);
		/*
		return actor_repo
				.findById(actor_id)
				.orElseThrow(() -> new ResourceAccessException("Actor not found"));
		 */
	}

	@PostMapping("/addFilm")
	public Film AddFilm(@RequestBody Film new_film)
	{
		return film_repo.save(new_film);
	}

	@DeleteMapping("/deleteFilm/{id}")
	public Film DeleteFilm(@PathVariable("id") Integer film_id)
	{
		Film film = GetFilm(film_id);

		if(film == null) return null;

		film_repo.deleteById(film_id);

		return film;

	}

	@PutMapping("/addActorToFilm")
	public Film AddActorToFilm(@RequestBody Map<String,Integer> film_actor_request)
	{
		Film film = film_repo.findById(film_actor_request.get("film_id")).orElse(null);
		Actor actor = actor_repo.findById(film_actor_request.get("actor_id")).orElse(null);

		if(film == null || actor == null) return null;

		// System.out.println(film + " " + actor);

		Set<Actor> all_actors = film.getActors();
		all_actors.add(actor);
		film.setActors(all_actors);
		film_repo.save(film);

		return GetFilm(film.getFilm_id());
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
		List<Set<Actor>> all_actors = new LinkedList<>();
		for(Film film : this.film_repo.findAll())
		{
			all_actors.add(film.getActors());
		}
		System.out.println(all_actors.size());

		return all_actors;
	}
	@GetMapping("/allActorFilms")
	public Iterable<Set<Film>> allActorFilms()
	{
		List<Set<Film>> all_films = new LinkedList<>();
		for(Actor actor : this.actor_repo.findAll())
		{
			all_films.add(actor.getFilms());
		}

		System.out.println(all_films.size());
		return all_films;
	}


	@GetMapping("/allFilmsByActorID/{id}")
	public Set<Film> AllFilmsActorID(@PathVariable("id") Integer actor_id)
	{
		Actor actor = this.actor_repo.findById(actor_id).orElse(null);
		if(actor == null) return null;

		return actor.getFilms();
	}

	@GetMapping("/allFilmsByActorName")
	public List<Set<Film>> AllFilmsActorName(@RequestBody Actor actor_input)
	{
		List<Actor> matching_actors = GetActorByName(actor_input);

		// Get all films by every actor of matching name
		List<Set<Film>> all_films_by_actor = new LinkedList<>();
		for(Actor actor : matching_actors)
		{
			all_films_by_actor.add(actor.getFilms());
		}
		return all_films_by_actor;
	}




	// ====== COUNTRIES + CITIES =====
	@GetMapping("getCountries")
	public Iterable<Country> GetCountries()
	{
		return this.country_repo.findAll();
	}
	@GetMapping("getCities")
	public Iterable<City> GetCities()
	{
		return this.city_repo.findAll();
	}

	@GetMapping("getCitiesByCountry")
	public Iterable<City> GetCitiesCountry(@RequestBody Country country_input)
	{

		for(Country country : this.country_repo.findAll())
		{
			if(country.getCountry().equals(country_input.getCountry()))
			{
				return country.getCities();
			}
		}

		return null;
	}

	@GetMapping("getCitiesByCountryQuery")
	public Iterable<City> GetCitiesCountryQuery(@RequestBody Country country_input)
	{
		for(Country country : this.country_repo.findAll())
		{
			if(country.getCountry().equals(country_input.getCountry()))
			{
				return country_repo.getCityQuery(country);
			}
		}
		return null;


	}


}
