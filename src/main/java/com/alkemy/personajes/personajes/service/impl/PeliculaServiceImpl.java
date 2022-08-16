package com.alkemy.personajes.personajes.service.impl;

import com.alkemy.personajes.personajes.dto.*;
import com.alkemy.personajes.personajes.entity.PeliculaEntity;
import com.alkemy.personajes.personajes.entity.PersonajeEntity;
import com.alkemy.personajes.personajes.exception.ParamNotFound;
import com.alkemy.personajes.personajes.mapper.PeliculaMapper;
import com.alkemy.personajes.personajes.repository.PeliculaRepository;
import com.alkemy.personajes.personajes.repository.specification.PeliculaSpecification;
import com.alkemy.personajes.personajes.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaServiceImpl implements PeliculaService {

    PeliculaMapper peliculaMapper;
    PeliculaRepository peliculaRepository;
    PeliculaSpecification peliculaSpecification;
    @Autowired
    public PeliculaServiceImpl(PeliculaMapper peliculaMapper,
                               PeliculaRepository peliculaRepository,
                               PeliculaSpecification peliculaSpecification){
        this.peliculaMapper = peliculaMapper;
        this.peliculaRepository = peliculaRepository;
        this.peliculaSpecification = peliculaSpecification;
    }
    @Override
    public PeliculaDTO save(PeliculaDTO peliculaDTO) {
        PeliculaEntity entity =  this.peliculaMapper.peliculaDTO2Entity(peliculaDTO);
        PeliculaEntity entitySaved = this.peliculaRepository.save(entity);
        PeliculaDTO result = this.peliculaMapper.peliculaEntity2DTO(entitySaved, true);
        return result;
    }

    @Override
    public void delete(Long id) {
        this.peliculaRepository.deleteById(id);
    }

    @Override
    public PeliculaDTO update(Long id, PeliculaDTO peliculaDTO) {
        Optional<PeliculaEntity> entity= this.peliculaRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("Id Icono no valido");
        }
        this.peliculaMapper.peliculaEntityRefreshValues(entity.get(), peliculaDTO);
        PeliculaEntity entitySaved = this.peliculaRepository.save(entity.get());
        PeliculaDTO result = this.peliculaMapper.peliculaEntity2DTO(entitySaved, true);

        return result;
    }
    public PeliculaDTO getDetailsById(Long id) {
        Optional<PeliculaEntity> entity = this.peliculaRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("Id película no válido");
        }
        PeliculaDTO peliculaDTO = this.peliculaMapper.peliculaEntity2DTO(entity.get(),true);
        return peliculaDTO;
    }

    public List<PeliculaBasicDTO> getByFilters(String name, String genre, String order) {
        PeliculaFiltersDTO filtersDTO = new PeliculaFiltersDTO(name, genre, order);
        List<PeliculaEntity> entities = this.peliculaRepository.findAll(this.peliculaSpecification.getByFilters(filtersDTO));
        List<PeliculaBasicDTO> dtos = this.peliculaMapper.peliculaEntityList2BasicDTOList(entities);
        return dtos;
    }
//    public PeliculaEntity getEntityById(Long id) {
//        Optional<PeliculaEntity> entity = peliculaRepository.findById(id);
//        return entity.get();
//    }





}
