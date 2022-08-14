package com.alkemy.personajes.personajes.repository;

import com.alkemy.personajes.personajes.entity.PeliculaEntity;
import com.alkemy.personajes.personajes.entity.PersonajeEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PeliculaRepository extends JpaRepository<PeliculaEntity, Long>, JpaSpecificationExecutor<PeliculaEntity> {
    List<PeliculaEntity> findAll(Specification<PeliculaEntity> spec);
}
