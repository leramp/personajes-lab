package com.alkemy.personajes.personajes.service.impl;

import com.alkemy.personajes.personajes.dto.GeneroDTO;
import com.alkemy.personajes.personajes.entity.GeneroEntity;
import com.alkemy.personajes.personajes.exception.ParamNotFound;
import com.alkemy.personajes.personajes.mapper.GeneroMapper;
import com.alkemy.personajes.personajes.repository.GeneroRepository;
import com.alkemy.personajes.personajes.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

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
    //    @Override
//    public void delete(Long id) {
//        Optional<GeneroEntity> entity= this.generoRepository.findById(id);
//        if(!entity.isPresent()){
//            throw new ParamNotFound("Id Genero no valido");
//        }
//        this.generoRepository.deleteById(id);
//
//    }

    @Override
    public List<GeneroDTO> getAllGeneros() {
        List<GeneroEntity> entities = generoRepository.findAll();
        List<GeneroDTO> result = generoMapper.generoEntity2DTOList(entities);
        return result;
    }
    @Override
    public GeneroDTO update(Long id, GeneroDTO generoDTO) {
        Optional<GeneroEntity> entity= this.generoRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("Id Genero no valido");
        }
        this.generoMapper.generoEntityRefreshValues(entity.get(), generoDTO);
        GeneroEntity entitySaved = this.generoRepository.save(entity.get());
        GeneroDTO result = this.generoMapper.generoEntity2DTO(entitySaved);

        return result;
    }


}
