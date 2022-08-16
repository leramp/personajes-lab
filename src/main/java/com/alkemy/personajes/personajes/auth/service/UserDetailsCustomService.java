package com.alkemy.personajes.personajes.auth.service;

import com.alkemy.personajes.personajes.auth.dto.UserDTO;
import com.alkemy.personajes.personajes.auth.entity.UserEntity;
import com.alkemy.personajes.personajes.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
@Service
public class UserDetailsCustomService implements UserDetailsService {//lo importante es que tiene que implementar esto
    @Autowired
    private UserRepository userRepository;//lo tenemos para ir a buscar usuarios, esto lo creamos nosotros
 //   @Autowired
//    private EmailService emailService;

    @Override//sobreescribo como voy a ir a buscar el usuario por el nombre cuando llegue
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException{
        UserEntity userEntity = userRepository.findByUsername(userName);
        if(userEntity==null){
            throw new UsernameNotFoundException("Username or password not found");
        }
        return new User(userEntity.getUsername(), userEntity.getPassword(), Collections.emptyList());
        //el collections que le paso son los roles, pero como no lo vamos a ver le pasamos una lista vacía
        //entonces busco un nuevo usuario y si lo encuentro genero un nuevo usuario que lo vamos a manejar
        //en el filtro de la configuración
    }
    public boolean save(UserDTO userDTO){
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(userDTO.getUsername());
        userEntity.setPassword(userDTO.getPassword());
        userEntity = this.userRepository.save(userEntity);
//        if(userEntity != null){
//            emailService.sendWelcomeEmailTo(userEntity.getUser());
//        }
        return userEntity != null;
    }
}

