package com.alkemy.personajes.personajes.auth.repository;

import com.alkemy.personajes.personajes.auth.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<UserEntity, Long> {//este repo maneja userEntity
    UserEntity findByUsername(String username);
}

