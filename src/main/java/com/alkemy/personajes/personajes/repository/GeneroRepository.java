package com.alkemy.personajes.personajes.repository;

import com.alkemy.personajes.personajes.entity.GeneroEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface GeneroRepository extends JpaRepository<GeneroEntity, Long> {
}
