package com.example.RESTSpring.Actor;

import com.example.RESTSpring.Film.Film;
import com.fasterxml.jackson.annotation.*;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name="actor")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "actor_id")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Actor {

    @Id
    @Column(name="actor_id",unique=true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer actorId;

    @Column(name="first_name")
    private String firstName;

    @Column(name="last_name")
    private String lastName;


    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "film_actor",
            joinColumns = { @JoinColumn(name = "actor_id", referencedColumnName = "actor_id") },
            inverseJoinColumns = { @JoinColumn(name = "film_id", referencedColumnName =  "film_id") }
    )
    // @JsonManagedReference
    @JsonIgnore
    private Set<Film> films = new HashSet<>();

    public Actor(){}
    public Actor(Integer actorId) {
        this.actorId = actorId;
    }
    public Actor(ActorDTO actor_dto)
    {
        this.updateActor(actor_dto);
    }

    public void updateActor(ActorDTO actorDTO)
    {
       if(actorDTO.getFirstName() != null) this.firstName = actorDTO.getFirstName();
       if(actorDTO.getLastName() != null)  this.lastName = actorDTO.getLastName();
    }


    public Integer getActorId() {
        return actorId;
    }
    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public Set<Film> getFilms() {
        return films;
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
    public void setFilms(Set<Film> films) {
        this.films = films;
    }
}
