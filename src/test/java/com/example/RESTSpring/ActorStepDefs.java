package com.example.RESTSpring;

import com.example.RESTSpring.Actor.Actor;
import com.example.RESTSpring.Actor.ActorDTO;
import com.example.RESTSpring.Actor.ActorRepository;
import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ActorStepDefs {

    private Map<Integer, Set<Integer>> mockActorRepoistory;
    private Map<Integer, Set<Integer>> mockFilmRepositoru;
    private Actor actor;

    private String inputFirstName;
    private String inputLastName;


    @Given("first name is set to {string}")
    public void GivenFirstName(String firstName) {
        this.inputFirstName = firstName;
    }

    @Given("last name is set to {string}")
    public void GivenLastName(String lastName) {
        this.inputLastName = lastName;
    }

    @Given("an Actor called {string} {string}")
    public void GivenActor(String firstName, String lastName) {
        this.actor = new Actor();
        this.actor.setFirstName(firstName);
        this.actor.setLastName(lastName);
    }

    @Given("the following Films exist:")
    public void GivenFilmsExist(DataTable filmsTable) {
        this.mockFilmRepositoru = new HashMap<>();

        List<Map<String, String>> rows = filmsTable.asMaps(String.class, String.class);
        for(Map<String, String> film : rows) {
            Integer filmId = Integer.valueOf(film.get("filmId"));
            Set<Integer> actorIds = Arrays.stream(film.get("actorIds").split(","))
                    .map(Integer::valueOf)
                    .collect(Collectors.toSet());

            this.mockFilmRepositoru.put(filmId, actorIds);
        }
    }

    @Given("the following Actors exist:")
    public void GivenActorsExist(DataTable actorsTable) {
        this.mockActorRepoistory = new HashMap<>();

        List<Map<String, String>> rows = actorsTable.asMaps(String.class, String.class);
        for(Map<String, String> actors : rows) {
            Integer actorId = Integer.valueOf(actors.get("actorId"));
            Set<Integer> filmIds = Arrays.stream(actors.get("filmIds").split(","))
                    .map(Integer::valueOf)
                    .collect(Collectors.toSet());

            this.mockActorRepoistory.put(actorId, filmIds);
        }
    }


    @When("I create a new Actor")
    public void WhenCreateNewActor() {
        this.actor = new Actor();
        this.actor.setFirstName(this.inputFirstName);
        this.actor.setLastName(this.inputLastName);
    }

    @When("I update Actor's name to {string} {string}")
    public void WhenUpdateFirstName(String firstName, String lastName){
        ActorDTO actorDTO = new ActorDTO();
        actorDTO.setFirstName(firstName);
        actorDTO.setLastName(lastName);

        this.actor.updateActor(actorDTO);
    }

    @When("I add Film {int} to Actor {int}")
    public void WhenAddFilmToActor(Integer filmId, Integer actorId) {
        Set<Integer> allFilmsByActor = this.mockActorRepoistory.get(actorId);
        allFilmsByActor.add(filmId);
        this.mockActorRepoistory.put(actorId, allFilmsByActor);
    }


    @Then("the Actor names should be filled")
    public void ThenFilledActor()  {
        Assertions.assertNotNull(this.actor.getFirstName());
        Assertions.assertNotNull(this.actor.getLastName());
    }

    @Then("first name should be {string}")
    public void ThenMatchingFirstName(String expectedFirstName) {
        Assertions.assertEquals(expectedFirstName, this.actor.getFirstName());

    }

    @Then("last name should be {string}")
    public void ThenMatchingLastName(String expectedLastName) {
        Assertions.assertEquals(expectedLastName, this.actor.getLastName());

    }

    @Then("Actor {int} should be in Film {int}")
    public void ThenActorInFilm(Integer actorId, Integer filmId) {
        Set<Integer> allFilmsByActor = this.mockActorRepoistory.get(actorId);
        Assertions.assertTrue(allFilmsByActor.contains(filmId));
    }
    @Then("Actor {int} should not be in Film {int}")
    public void ThenActorNotInFilm(Integer actorId, Integer filmId) {
        Set<Integer> allFilmsByActor = this.mockActorRepoistory.get(actorId);
        Assertions.assertFalse(allFilmsByActor.contains(filmId));
    }


/*
    @When("the client calls /user/1")
    public void ClientCallsGetUser()
    {
        executeGet("http://localhost:8080/version");
    }
*/
}
