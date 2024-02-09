package com.example.RESTSpring;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;

@Entity
@Table(name="city")
@JsonIdentityInfo(
        generator = ObjectIdGenerators.PropertyGenerator.class,
        property = "city_id")
public class City {
    public City(){}

    @Id
    @Column(name="city_id",unique=true)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer city_id;

    @Column(name="city")
    private String city;

    @ManyToOne
    @JoinColumn(name="country_id")
    // @JsonIgnore
    private Country country;

    public Integer getCity_id() {
        return city_id;
    }

    public void setCity_id(Integer city_id) {
        this.city_id = city_id;
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
