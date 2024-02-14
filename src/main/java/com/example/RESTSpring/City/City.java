package com.example.RESTSpring.City;

import com.example.RESTSpring.Country.Country;
import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import jakarta.persistence.*;

@Entity
@Table(name="city")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "city_id")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class City {
    public City(){}

    @Id
    @Column(name="city_id",unique=true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cityId;

    @Column(name="city")
    private String city;

    @ManyToOne
    @JoinColumn(name="country_id")
    @JsonIgnore
    private Country country;

    public Integer getCityId() {
        return cityId;
    }

    public void setCityId(Integer cityId) {
        this.cityId = cityId;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }




}
