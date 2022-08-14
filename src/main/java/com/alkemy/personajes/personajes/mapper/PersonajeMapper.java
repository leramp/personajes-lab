package com.alkemy.personajes.personajes.mapper;

import com.alkemy.personajes.personajes.dto.PeliculaDTO;
import com.alkemy.personajes.personajes.dto.PersonajeBasicDTO;
import com.alkemy.personajes.personajes.dto.PersonajeDTO;
import com.alkemy.personajes.personajes.entity.PeliculaEntity;
import com.alkemy.personajes.personajes.entity.PersonajeEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.*;

@Component
public class PersonajeMapper {

    @Autowired
    private PeliculaMapper peliculaMapper;

//    @Autowired
//    public PersonajeMapper(PeliculaMapper peliculaMapper){
//        this.peliculaMapper = peliculaMapper;
//    }

    public PersonajeEntity personajeDTO2Entity(PersonajeDTO dto){
        PersonajeEntity personajeEntity = new PersonajeEntity();
        personajeEntity.setImagen((dto.getImagen()));
        personajeEntity.setNombre(dto.getNombre());
        personajeEntity.setEdad(dto.getEdad());
        personajeEntity.setPeso(dto.getPeso());
        personajeEntity.setHistoria(dto.getHistoria());
        return personajeEntity;

    }

    public PersonajeDTO personajeEntity2DTO (PersonajeEntity entity, boolean loadPeliculas){
        PersonajeDTO dto =  new PersonajeDTO();
        dto.setId(entity.getId());
        dto.setImagen((entity.getImagen()));
        dto.setNombre(entity.getNombre());
        dto.setEdad(entity.getEdad());
        dto.setPeso(entity.getPeso());
        dto.setHistoria(entity.getHistoria());
        if(loadPeliculas){
            List<PeliculaDTO> peliculasDTO = this.peliculaMapper.peliculaEntityList2DTOList(entity.getPeliculas(),false);
            dto.setPeliculas(peliculasDTO);
        }
        return dto;

    }
    public List<PersonajeDTO> personajeEntityList2DTOList (List<PersonajeEntity> entities, boolean loadPeliculas){
        List<PersonajeDTO> dtos = new ArrayList<>();
        for(PersonajeEntity entity :  entities){
            dtos.add(this.personajeEntity2DTO(entity, loadPeliculas));
        }
        return dtos;
    }
    public List<PersonajeBasicDTO> personajeEntityList2BasicDTOList(List<PersonajeEntity> entities){
        List<PersonajeBasicDTO> dtos = new ArrayList<>();
        PersonajeBasicDTO basicDTO;
        for(PersonajeEntity entity : entities){
            basicDTO=new PersonajeBasicDTO();
            basicDTO.setImagen(entity.getImagen());
            basicDTO.setNombre(entity.getNombre());
            dtos.add(basicDTO);
        }
        return dtos;
    }
    public void personajeEntityRefreshValues(PersonajeEntity entity, PersonajeDTO personajeDTO){
        entity.setImagen(personajeDTO.getImagen());
        entity.setNombre(personajeDTO.getNombre());
        entity.setEdad(personajeDTO.getEdad());
        entity.setPeso(personajeDTO.getPeso());
        entity.setHistoria(personajeDTO.getHistoria());
    }

    public List<PersonajeEntity> personajeDTOList2EntityList(List<PersonajeDTO> dtos) {
        List<PersonajeEntity> entities = new ArrayList<>();
        for(PersonajeDTO dto : dtos){
            entities.add(this.personajeDTO2Entity(dto));
        }
        return entities;
    }
}
