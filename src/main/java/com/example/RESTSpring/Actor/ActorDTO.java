package com.example.RESTSpring.Actor;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;

import java.util.Set;

@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ActorDTO {
    private Integer actorId;
    private String firstName;
    private String lastName;

    private Set<Integer> filmIds;


    public Integer getActorId() {
        return this.actorId;
    }

    public String getFirstName() {
        return this.firstName;
    }

    public String getLastName() {
        return this.lastName;
    }

    public Set<Integer> getFilmIds() {
        return this.filmIds;
    }


    public void setActorId(Integer actorId) {
        this.actorId = actorId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName.toUpperCase();
    }

    public void setLastName(String lastName) {
        this.lastName = lastName.toUpperCase();
    }

    public void setFilmIds(Set<Integer> filmIds) {
        this.filmIds = filmIds;
    }
}