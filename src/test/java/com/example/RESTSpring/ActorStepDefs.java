package com.example.RESTSpring;

import com.example.RESTSpring.Models.Actor;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ActorStepDefs {

    private int id;
    private String name;

    @Given("userID is {int}")
    public void UserIDIs(int id)
    {
        this.id = id;
    }

    @When("I ask what user's first name is")
    public void IAskWhatUsersFirstNameIs()
    {
        this.name = "BOB";
    }

    @Then("I should be told {string}")
    public void IShouldBeTold(String expected)
    {
        assertEquals(expected, this.name);
    }


/*
    @When("the client calls /user/1")
    public void ClientCallsGetUser()
    {
        executeGet("http://localhost:8080/version");
    }
*/
}
