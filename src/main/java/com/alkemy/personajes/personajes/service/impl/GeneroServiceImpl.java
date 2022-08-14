package com.alkemy.personajes.personajes.service.impl;

import com.alkemy.personajes.personajes.dto.GeneroDTO;
import com.alkemy.personajes.personajes.entity.GeneroEntity;
import com.alkemy.personajes.personajes.mapper.GeneroMapper;
import com.alkemy.personajes.personajes.repository.GeneroRepository;
import com.alkemy.personajes.personajes.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GeneroServiceImpl implements GeneroService {

    @Autowired
    GeneroMapper generoMapper;
    @Autowired
    GeneroRepository generoRepository;


    public GeneroDTO save(GeneroDTO dto){

        GeneroEntity entity =  generoMapper.generoDTO2Entity(dto);
        GeneroEntity entitySaved=generoRepository.save(entity);
        GeneroDTO result =  generoMapper.generoEntity2DTO(entitySaved);
        System.out.println("GUARDAR GENERO");
        return result;
    }


}
