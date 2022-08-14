package com.alkemy.personajes.personajes.mapper;

import com.alkemy.personajes.personajes.dto.PeliculaBasicDTO;
import com.alkemy.personajes.personajes.dto.PeliculaDTO;
import com.alkemy.personajes.personajes.dto.PersonajeBasicDTO;
import com.alkemy.personajes.personajes.dto.PersonajeDTO;
import com.alkemy.personajes.personajes.entity.PeliculaEntity;
import com.alkemy.personajes.personajes.entity.PersonajeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Component
public class PeliculaMapper {

    @Autowired
    private PersonajeMapper personajeMapper;
//    @Autowired
//    public PeliculaMapper(
//            PersonajeMapper personajeMapper
//    )
//    {
//        this.personajeMapper = personajeMapper;
//    }
    public List<PeliculaDTO> peliculaEntityList2DTOList(List<PeliculaEntity> entities, boolean loadPersonajes){
        List<PeliculaDTO> dtos = new ArrayList<>();
        for(PeliculaEntity entity : entities){
            dtos.add(this.peliculaEntity2DTO(entity, loadPersonajes));
        }
        return dtos;
    }


    public PeliculaDTO peliculaEntity2DTO(PeliculaEntity entity, boolean loadPersonajes){
        PeliculaDTO dto = new PeliculaDTO();
        dto.setId((entity.getId()));
        dto.setImagen(entity.getImagen());
        dto.setTitulo(entity.getTitulo());
        dto.setFechaCreacion(entity.getFechaCreacion().toString());
        dto.setGeneroId(entity.getGeneroId());
        dto.setCalificacion(entity.getCalificacion());
        if(loadPersonajes){
            List<PersonajeDTO> personajesDTO =  this.personajeMapper.personajeEntityList2DTOList(entity.getPersonajes(), false);
            dto.setPersonajes(personajesDTO);
        }
        return dto;
    }


    private LocalDate string2LocalDate(String stringDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(stringDate, formatter);
        return date;
    }


    public PeliculaEntity peliculaDTO2Entity(PeliculaDTO dto) {
        PeliculaEntity peliculaEntity =  new PeliculaEntity();
        peliculaEntity.setImagen(dto.getImagen());
        peliculaEntity.setTitulo(dto.getTitulo());
        peliculaEntity.setFechaCreacion(this.string2LocalDate(dto.getFechaCreacion()));
        peliculaEntity.setGeneroId(dto.getGeneroId());
        peliculaEntity.setCalificacion(dto.getCalificacion());
        peliculaEntity.setPersonajes(personajeMapper.personajeDTOList2EntityList(dto.getPersonajes()));
        return peliculaEntity;


    }
    public void peliculaEntityRefreshValues(PeliculaEntity entity, PeliculaDTO dto){
        entity.setImagen(dto.getImagen());
        entity.setTitulo(dto.getTitulo());
        entity.setFechaCreacion(this.string2LocalDate(dto.getFechaCreacion()));
        entity.setGeneroId(dto.getGeneroId());
        entity.setCalificacion(dto.getCalificacion());
    }


    public List<PeliculaBasicDTO> peliculaEntityList2BasicDTOList(List<PeliculaEntity> entities){
        List<PeliculaBasicDTO> dtos = new ArrayList<>();
        PeliculaBasicDTO basicDTO;
        for(PeliculaEntity entity : entities){
            basicDTO=new PeliculaBasicDTO();
            basicDTO.setImagen(entity.getImagen());
            basicDTO.setTitulo(entity.getTitulo());
            basicDTO.setFechaCreacion(entity.getFechaCreacion().toString());
            dtos.add(basicDTO);
        }
        return dtos;
    }
}
