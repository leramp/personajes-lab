package com.alkemy.personajes.personajes.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Set;

@Getter
@Setter
public class PersonajeFiltersDTO {

    private String name;
    private String age;
    private List<Long> movies;
    private String order;

    public PersonajeFiltersDTO(String name, String age, List<Long> movies, String order) {
        this.name = name;
        this.age = age;
        this.movies = movies;
        this.order = order;
    }
    public boolean isASC(){return this.order.compareToIgnoreCase("ASC")==0;}
    public boolean isDESC(){return this.order.compareToIgnoreCase("DESC")==0;}
}
