package com.alkemy.personajes.personajes.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="peliculas")
@SQLDelete(sql="UPDATE peliculas SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
@Getter
@Setter
public class PeliculaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private boolean deleted = Boolean.FALSE;

    private String imagen;

    private String titulo;

    @Column(name="fecha_creacion")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private LocalDate fechaCreacion;



    @ManyToOne(

            //fetch = FetchType.EAGER
            //cascade = CascadeType.ALL
            //el EAGER esta mal porque cuando recupero una pelicula no quiero los personajes porque puede
            //ser costoso
            //no va ALL porque no quiero que se borre el géro cuando borro la peli
            //y no quiero que se cree un genero cuando se crea la pelicula
            //voy a usar ALL cuando a una persona le creo el campo direccion, en donde
            //esta bien que entren juntos y se vayan juntos. si se borra la persona
            //y no la dirección entonces esta queda huérfana. La relacion es acoplada. El género no
            //y no va PERSIST porque no quiero crear un genero a partir de una peli
    )
    @JoinColumn(name= "genero_id", insertable = false, updatable = false)
    private GeneroEntity genero;

    @Column (name="genero_id", nullable = false)
    private Long generoId;

    private int calificacion;
    public void setCalificacion(int calificacion)
    {
        if(calificacion>0 && calificacion<6) this.calificacion = calificacion;
    }
    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,//significa que cuando creo una peli creo personajes
                    //si no se pone esto hibernate no hace los insert
                    CascadeType.MERGE//esto es para el update
            })
    //ManyToManny y ManyToOne va de lo mas permisivo() a lo mas restrictivo (..,..,..,etc)
    @JoinTable(
            name="personaje_pelicula",
            joinColumns = @JoinColumn(name="pelicula_id"),
            inverseJoinColumns = @JoinColumn(name = "personaje_id"))
    private List<PersonajeEntity> personajes = new ArrayList<>();

    @Override
    public boolean equals(Object obj){
        if(obj == null)
            return false;
        if(getClass()!= obj.getClass())
            return false;
        final PeliculaEntity other = (PeliculaEntity) obj;
        return other.id == this.id;
    }
}
