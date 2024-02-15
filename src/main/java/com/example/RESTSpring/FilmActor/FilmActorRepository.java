package com.example.RESTSpring.FilmActor;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

public interface FilmActorRepository extends JpaRepository<FilmActor, FilmActorKey> {

    Set<FilmActor> findByFilmActorKeyFilmId(Integer filmId);
    Set<FilmActor> findByFilmActorKeyActorId(Integer actorId);

    @Transactional
    void deleteByFilmActorKeyFilmId(Integer filmId);
    @Transactional
    void deleteByFilmActorKeyActorId(Integer actorId);

}
