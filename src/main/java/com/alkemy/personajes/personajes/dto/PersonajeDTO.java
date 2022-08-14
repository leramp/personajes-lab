package com.alkemy.personajes.personajes.dto;

import com.alkemy.personajes.personajes.entity.PeliculaEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class PersonajeDTO {

    private Long id;
    private String imagen;
    private String nombre;
    private int edad;
    private Long peso;
    private String historia;
    private List<PeliculaDTO> peliculas;
}
