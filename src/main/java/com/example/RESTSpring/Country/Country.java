package com.example.RESTSpring.Country;

import com.example.RESTSpring.City.City;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(name="country")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "country_id")
public class Country {
    public Country(){}

    @Id
    @Column(name="country_id",unique=true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer countryId;

    @Column(name="country")
    private String country;

    @OneToMany(mappedBy = "country")
    @JsonIgnore
    private List<City> cities;



    public List<City> getCityQuery(String Country){
        return null;
    }

    public Integer getCountryId() {
        return countryId;
    }

    public void setCountryId(Integer countryId) {
        this.countryId = countryId;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public List<City> getCities() {
        return cities;
    }

    public void setCities(List<City> cities) {
        this.cities = cities;
    }


}
