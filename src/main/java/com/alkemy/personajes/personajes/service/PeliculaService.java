package com.alkemy.personajes.personajes.service;

import com.alkemy.personajes.personajes.dto.PeliculaBasicDTO;
import com.alkemy.personajes.personajes.dto.PeliculaDTO;
import com.alkemy.personajes.personajes.dto.PersonajeDTO;
import org.springframework.stereotype.Service;

import java.util.List;


public interface PeliculaService {

    PeliculaDTO save(PeliculaDTO peliculaDTO);

    void delete(Long id);

    PeliculaDTO update(Long id, PeliculaDTO dto);

    PeliculaDTO getDetailsById(Long id);

    List<PeliculaBasicDTO> getByFilters(String name, String genre, String order);
}
