package com.example.RESTSpring.Country;

import com.example.RESTSpring.City.City;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="country")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "country_id")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class Country {

    @Id
    @Column(name="country_id",unique=true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer countryId;

    @Column(name="country")
    private String country;

    @OneToMany(mappedBy = "country")
    @JsonIgnore
    private List<City> cities;


    public Country(){}


    public Integer getCountryId() {
        return countryId;
    }

    public String getCountry() {
        return country;
    }

    public List<City> getCities() {
        return cities;
    }


    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }


}
