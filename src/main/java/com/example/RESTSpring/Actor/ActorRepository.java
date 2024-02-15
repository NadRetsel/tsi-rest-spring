package com.example.RESTSpring.Actor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface ActorRepository extends JpaRepository<Actor,Integer>{

    @Query("SELECT a FROM Actor a " +
            "WHERE (:firstName IS NULL OR a.firstName = UPPER(:firstName)) " +
            "AND (:lastName IS NULL OR a.lastName = UPPER(:lastName))" +
            "AND NOT(:firstName IS NULL AND :lastName IS NULL)")
    Set<Actor> getByName(String firstName, String lastName);


}
