package com.alkemy.personajes.personajes.service.impl;

import com.alkemy.personajes.personajes.dto.PersonajeBasicDTO;
import com.alkemy.personajes.personajes.dto.PersonajeDTO;
import com.alkemy.personajes.personajes.dto.PersonajeFiltersDTO;
import com.alkemy.personajes.personajes.entity.PeliculaEntity;
import com.alkemy.personajes.personajes.entity.PersonajeEntity;
import com.alkemy.personajes.personajes.exception.ParamNotFound;
import com.alkemy.personajes.personajes.mapper.PersonajeMapper;
import com.alkemy.personajes.personajes.repository.PersonajeRepository;
import com.alkemy.personajes.personajes.repository.specification.PersonajeSpecification;
import com.alkemy.personajes.personajes.service.PeliculaService;
import com.alkemy.personajes.personajes.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PersonajeServiceImpl implements PersonajeService {
    private PersonajeRepository personajeRepository;
    private PersonajeMapper personajeMapper;

    private PersonajeSpecification personajeSpecification;
//    private PersonajeEntity personajeEntity;
//    private PeliculaEntity peliculaEntity;
//    private PeliculaService peliculaService;


    @Autowired
    public PersonajeServiceImpl(
            PersonajeRepository personajeRepository,
            PersonajeSpecification personajeSpecification,
            PersonajeMapper personajeMapper
//            PersonajeEntity personajeEntity,
//            PeliculaEntity peliculaEntity,
//            PeliculaService peliculaService

    )
    {
        this.personajeRepository = personajeRepository;
        this.personajeSpecification = personajeSpecification;
        this.personajeMapper = personajeMapper;
//        this.peliculaService = peliculaService;
//        this.personajeEntity = personajeEntity;
//        this.peliculaEntity = peliculaEntity;

    }

    @Override
    public PersonajeDTO save(PersonajeDTO personajeDTO) {
        PersonajeEntity entity = this.personajeMapper.personajeDTO2Entity(personajeDTO);
        PersonajeEntity entitySaved = this.personajeRepository.save(entity);
        PersonajeDTO result = this.personajeMapper.personajeEntity2DTO(entitySaved, false);
        return result;
    }

    @Override
    public void delete(Long id) {
        this.personajeRepository.deleteById(id);
    }

    @Override
    public PersonajeDTO update(Long id, PersonajeDTO personajeDTO) {
        Optional<PersonajeEntity> entity= this.personajeRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("Id Icono no valido");
        }
        this.personajeMapper.personajeEntityRefreshValues(entity.get(), personajeDTO);
        PersonajeEntity entitySaved = this.personajeRepository.save(entity.get());
        PersonajeDTO result = this.personajeMapper.personajeEntity2DTO(entitySaved, true);

        return result;
    }

    @Override
    public PersonajeDTO getDetailsById(Long id) {
        Optional<PersonajeEntity> entity = this.personajeRepository.findById(id);
        if(!entity.isPresent()){
            throw new ParamNotFound("Id personaje no válido");
        }
        PersonajeDTO personajeDTO = this.personajeMapper.personajeEntity2DTO(entity.get(),true);
        return personajeDTO;
    }

    @Override
    public List<PersonajeBasicDTO> getByFilters(String name, String age, List<Long> movies, String order) {
            PersonajeFiltersDTO filtersDTO = new PersonajeFiltersDTO(name, age, movies, order);
            List<PersonajeEntity> entities = this.personajeRepository.findAll(this.personajeSpecification.getByFilters(filtersDTO));
            List<PersonajeBasicDTO> dtos = this.personajeMapper.personajeEntityList2BasicDTOList(entities);
            return dtos;
    }
//    public PersonajeDTO addPelicula(Long idPersonaje, Long idPelicula){
//
//        PersonajeEntity personajeEntity = this.getEntityById(idPersonaje);
//
//        PeliculaEntity peliculaEntity = this.peliculaService.getEntityById(idPelicula);
//        personajeEntity.addPelicula(peliculaEntity);
//
//        PersonajeEntity entitySaved= this.personajeRepository.save(personajeEntity);
//        PersonajeDTO result = this.personajeMapper.personajeEntity2DTO(entitySaved, true);
//        return result;
//    }
//    public PersonajeEntity getEntityById(Long id){
//        Optional<PersonajeEntity> entity = personajeRepository.findById(id);
//        return entity.get();
//    }
//    public void removePelicula(Long idPersonaje, Long idPelicula){
//        PersonajeEntity personajeEntity = this.getEntityById(idPersonaje);
//        PeliculaEntity peliculaEntity = this.peliculaService.getEntityById(idPelicula);
//        personajeEntity.removePelicula(peliculaEntity);
//    }


}
