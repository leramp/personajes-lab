package com.alkemy.personajes.personajes.controller;

import com.alkemy.personajes.personajes.dto.GeneroDTO;
import com.alkemy.personajes.personajes.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("generos")
public class GeneroController {

    @Autowired
    GeneroService generoService;

    @PostMapping
    public ResponseEntity<GeneroDTO> save(@RequestBody GeneroDTO genero){ //para acceder al body desde nuestro codigo y definimos el tipo de variable
        GeneroDTO generoGuardado = generoService.save(genero);
        //save continente
        return ResponseEntity.status(HttpStatus.CREATED).body(generoGuardado);
        //este continente se pone en el body y responde como creado
    }
}
