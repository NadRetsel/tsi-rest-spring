package com.example.RESTSpring.Country;

import com.example.RESTSpring.City.City;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CountryRepository extends JpaRepository<Country,Integer> {

    @Query("SELECT c FROM City c WHERE c.country = ?1")
    List<City> getCityQuery(Country country);
}
