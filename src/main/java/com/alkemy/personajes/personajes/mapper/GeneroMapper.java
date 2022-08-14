package com.alkemy.personajes.personajes.mapper;

import com.alkemy.personajes.personajes.dto.GeneroDTO;
import com.alkemy.personajes.personajes.entity.GeneroEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class GeneroMapper {


    public GeneroEntity generoDTO2Entity(GeneroDTO dto){
        GeneroEntity generoEntity = new GeneroEntity();
        generoEntity.setImagen((dto.getImagen()));
        generoEntity.setNombre(dto.getNombre());
        return generoEntity;
    }
    public GeneroDTO generoEntity2DTO(GeneroEntity entity){
        GeneroDTO dto = new GeneroDTO();
        dto.setId((entity.getId()));
        dto.setImagen(entity.getImagen());
        dto.setNombre((entity.getNombre()));
        return dto;
    }
}
