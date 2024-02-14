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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.*;

@SpringBootApplication
@RestController
@RequestMapping("/home")
@CrossOrigin
public class RestSpringApplication {



	@Autowired
	private ActorRepository actor_repo;
	@Autowired
	private FilmRepository film_repo;
	@Autowired
	private CountryRepository country_repo;
	@Autowired
	private CityRepository city_repo;

	public RestSpringApplication(ActorRepository actor_repo, FilmRepository film_repo, CountryRepository country_repo, CityRepository city_repo) {
		this.actor_repo = actor_repo;
		this.film_repo = film_repo;
		this.country_repo = country_repo;
		this.city_repo = city_repo;

	}

	public static void main(String[] args) {
		SpringApplication.run(RestSpringApplication.class, args);
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
