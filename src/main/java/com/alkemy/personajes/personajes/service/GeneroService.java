package com.alkemy.personajes.personajes.service;

import com.alkemy.personajes.personajes.dto.GeneroDTO;

import java.util.List;

public interface GeneroService {
    GeneroDTO save(GeneroDTO dto);

    //void delete(Long id);

    List<GeneroDTO> getAllGeneros();

    GeneroDTO update(Long id, GeneroDTO generoDTO);
}
