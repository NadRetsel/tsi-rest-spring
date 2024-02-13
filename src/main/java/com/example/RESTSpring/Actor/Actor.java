package com.example.RESTSpring.Models;

import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;

import java.util.*;

@Entity
@Table(name="actor")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "actor_id")
public class Actor {
    public Actor(){}

    @Id
    @Column(name="actor_id",unique=true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer actor_id;

    @Column(name="first_name")
    private String first_name;

    @Column(name="last_name")
    private String last_name;


    @ManyToMany(cascade = { CascadeType.ALL })
    @JoinTable(name = "film_actor",
            joinColumns = { @JoinColumn(name = "actor_id", referencedColumnName = "actor_id") },
            inverseJoinColumns = { @JoinColumn(name = "film_id", referencedColumnName =  "film_id") }
    )
    // @JsonManagedReference
    @JsonIgnore
    private Set<Film> films = new HashSet<>();



    public void setActor_id(Integer actor_id) {
        this.actor_id = actor_id;
    }

    public Set<Film> getFilms() {
        return films;
    }

    public void setFilms(Set<Film> films) {
        this.films = films;
    }

    public Integer getActor_id() {
        return actor_id;
    }

    public void setActor_id(int actor_id) {
        this.actor_id = actor_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }
}
