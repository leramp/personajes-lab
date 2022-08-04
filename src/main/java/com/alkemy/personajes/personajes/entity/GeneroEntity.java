package com.alkemy.personajes.personajes.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity//indca es es una entidad
@Table(name = "genero")//le digo cual es la tabla con la que relaciono la entidad
@Getter
@Setter
public class GeneroEntity {
    @Id
    //@Column //cuando el atributo se llama igual que la columna no hace falta
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nombre;

    private String imagen;

}
