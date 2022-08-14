package com.alkemy.personajes.personajes.dto;

import com.alkemy.personajes.personajes.entity.GeneroEntity;
import com.alkemy.personajes.personajes.entity.PersonajeEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Setter
@Getter
public class PeliculaDTO {
    private Long id;
    private String imagen;
    private String titulo;
    private String fechaCreacion;
    private Long generoId;
    private int calificacion;
    private List<PersonajeDTO> personajes;
}
