package com.example.RESTSpring;

import com.example.RESTSpring.Actor.Actor;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.jupiter.api.Assertions;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;


public class ActorStepDefs {

    private Actor new_actor;

    private String input_first_name;
    private String input_last_name;



    @Given("first name is set to {string}")
    public void FirstName(String first_name)
    {
        this.input_first_name = first_name;
    }
    @Given("last name is set to {string}")
    public void LastName(String last_name)
    {
        this.input_last_name = last_name;
    }

    @When("I create a new Actor")
    public void CreateNewActor()
    {
        this.new_actor = new Actor();
        this.new_actor.setFirst_name(this.input_first_name);
        this.new_actor.setLast_name(this.input_last_name);
    }

    @Then("the Actor names should be filled")
    public void FilledActor()  {
        Assertions.assertNotNull(this.new_actor.getFirst_name());
        Assertions.assertNotNull(this.new_actor.getLast_name());
    }
    @And("first name should be {string}")
    public void MatchingFirstName(String expexcted_first_name)
    {
        Assertions.assertEquals(expexcted_first_name, this.new_actor.getFirst_name());

    }
    @And("last name should be {string}")
    public void MatchingLastName(String expexcted_last_name)
    {
        Assertions.assertEquals(expexcted_last_name, this.new_actor.getLast_name());

    }


/*
    @When("the client calls /user/1")
    public void ClientCallsGetUser()
    {
        executeGet("http://localhost:8080/version");
    }
*/
}
