package com.example.RESTSpring.FilmActor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface FilmActorRepository extends JpaRepository<FilmActor, FilmActorKey> {
    Set<FilmActor> findByFilmActorKeyFilmId(Integer filmId);
}
