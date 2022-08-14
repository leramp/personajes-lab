package com.alkemy.personajes.personajes.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;
@Setter
@Getter
public class PeliculaFiltersDTO {
    private String name;
    private String genre;
    private String order;

    public PeliculaFiltersDTO(String name, String genre, String order){
        this.name=name;
        this.genre=genre;
        this.order=order;
    }
    public boolean isASC(){return this.order.compareToIgnoreCase("ASC") ==0;}
    public boolean isDESC(){ return this.order.compareToIgnoreCase("DESC")==0;}

}
