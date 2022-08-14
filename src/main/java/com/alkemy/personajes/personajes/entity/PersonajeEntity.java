package com.alkemy.personajes.personajes.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="personajes")
@Getter
@Setter
@SQLDelete(sql="UPDATE personajes SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class PersonajeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private boolean deleted = Boolean.FALSE;

    private String imagen;

    private String nombre;

    private int edad;

    private Long peso;

    private String historia;

    @ManyToMany(mappedBy = "personajes",
            fetch = FetchType.LAZY//no va EAGER por que puede ser costoso
            //cascade = CascadeType.ALL//está mal porque si borro un personaje borro una pelicula
            //cascade = CascadeType.PERSIST cuando guardo un personaje tambien persisto las peliculas
            //sin embargo esto no funciona de este lado, el no dueño de la relación (no explico por que pero es lo qu eme esta
            //pasando en el otro challenge, cuando le creo un pais a un icono no lo veo en la tabla intermedia
            //que persistir los personajes. no crea la tabla intermedia de este lado
    )


    private List<PeliculaEntity> peliculas = new ArrayList<>();

    public void addPelicula(PeliculaEntity pelicula){this.peliculas.add(pelicula);}
    public void removePelicula(PeliculaEntity pelicula){this.peliculas.remove(pelicula);}
}
