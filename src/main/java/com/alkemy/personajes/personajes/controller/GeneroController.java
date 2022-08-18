package com.alkemy.personajes.personajes.controller;

import com.alkemy.personajes.personajes.dto.GeneroDTO;
import com.alkemy.personajes.personajes.service.GeneroService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("generos")
public class GeneroController {

    @Autowired
    GeneroService generoService;

    @PostMapping
    public ResponseEntity<GeneroDTO> save(@RequestBody GeneroDTO genero){
        GeneroDTO generoGuardado = generoService.save(genero);
        return ResponseEntity.status(HttpStatus.CREATED).body(generoGuardado);

    }
    //    @DeleteMapping("/{id}")
//    public ResponseEntity<Void> delete(@PathVariable Long id){
//        this.generoService.delete(id);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }
    @PutMapping("/{id}")
    public ResponseEntity<GeneroDTO> update(@PathVariable Long id, @RequestBody GeneroDTO genero) {
        GeneroDTO result = this.generoService.update(id, genero);
        return ResponseEntity.ok().body(result);
    }
    @GetMapping
    public ResponseEntity<List<GeneroDTO>> getAll(){
        List<GeneroDTO> continentes = generoService.getAllGeneros();
        return ResponseEntity.ok().body(continentes);
    }
}
