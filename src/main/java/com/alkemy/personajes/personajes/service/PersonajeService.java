package com.alkemy.personajes.personajes.service;

import com.alkemy.personajes.personajes.dto.PersonajeBasicDTO;
import com.alkemy.personajes.personajes.dto.PersonajeDTO;

import java.util.List;

public interface PersonajeService {
    PersonajeDTO save(PersonajeDTO personajeDTO);

    void delete(Long id);

    PersonajeDTO update(Long id, PersonajeDTO personaje);

    PersonajeDTO getDetailsById(Long id);

    List<PersonajeBasicDTO> getByFilters(String name, String age, List<Long> movies, String order);
//    PersonajeDTO addPelicula(Long idPersonaje, Long idPelicula);
//    void removePelicula(Long idPersonaje, Long idPelicula);




}
