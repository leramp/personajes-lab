package com.alkemy.personajes.personajes.controller;

import com.alkemy.personajes.personajes.dto.PersonajeBasicDTO;
import com.alkemy.personajes.personajes.dto.PersonajeDTO;
import com.alkemy.personajes.personajes.service.PersonajeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("personajes")
public class PersonajeController {

    PersonajeService personajeService;

    @Autowired
    public PersonajeController(
            PersonajeService personajeService
    ){
        this.personajeService = personajeService;
    }


    @PostMapping //2
    public ResponseEntity<PersonajeDTO> saver(@RequestBody PersonajeDTO personaje) {
        PersonajeDTO result = this.personajeService.save(personaje);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @PutMapping("/{id}") //2
    public ResponseEntity<PersonajeDTO> update(@PathVariable Long id, @RequestBody PersonajeDTO personaje) {
        PersonajeDTO result = this.personajeService.update(id, personaje);
        return ResponseEntity.ok().body(result);
    }


    @DeleteMapping("/{id}") //2
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.personajeService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @GetMapping("/{id}") //4
    public ResponseEntity<PersonajeDTO> getDetailsById(@PathVariable Long id) {
        PersonajeDTO personajeDTO = this.personajeService.getDetailsById(id);
        return ResponseEntity.ok(personajeDTO);
    }
    @GetMapping //5
    public ResponseEntity<List<PersonajeBasicDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String age,
            @RequestParam(required = false) List<Long> movies,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {
        List<PersonajeBasicDTO> personajes = this.personajeService.getByFilters(name, age, movies, order);
        return ResponseEntity.ok(personajes);
    }
//    @PostMapping("/{id}/pelicula/{idPelicula}")
//    public ResponseEntity<PersonajeDTO> addPelicula(@PathVariable Long id, @PathVariable Long idPelicula) {
//        PersonajeDTO result = this.personajeService.addPelicula(id, idPelicula);
//        return ResponseEntity.status(HttpStatus.CREATED).body(result);
//    }
//
//    @DeleteMapping("/{id}/pelicula/{idPelicula}")
//    public ResponseEntity<Void> removePelicula(@PathVariable Long id, @PathVariable Long idPelicula) {
//        this.personajeService.removePelicula(id, idPelicula);
//        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
//    }


}
