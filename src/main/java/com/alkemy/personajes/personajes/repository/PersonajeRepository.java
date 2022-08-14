package com.alkemy.personajes.personajes.repository;

import com.alkemy.personajes.personajes.entity.PersonajeEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;

public interface PersonajeRepository extends JpaRepository <PersonajeEntity, Long>, JpaSpecificationExecutor<PersonajeEntity> {
    List<PersonajeEntity> findAll(Specification<PersonajeEntity> spec);
}
