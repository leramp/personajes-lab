package com.alkemy.personajes.personajes.controller;

import com.alkemy.personajes.personajes.dto.PeliculaBasicDTO;
import com.alkemy.personajes.personajes.dto.PeliculaDTO;
import com.alkemy.personajes.personajes.dto.PersonajeBasicDTO;
import com.alkemy.personajes.personajes.dto.PersonajeDTO;
import com.alkemy.personajes.personajes.service.PeliculaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("peliculas")
public class PeliculaController {

    PeliculaService peliculaService;

    @Autowired
    public PeliculaController(PeliculaService peliculaService){
        this.peliculaService = peliculaService;
    }

    @PostMapping //7
    public ResponseEntity<PeliculaDTO> saver(@RequestBody PeliculaDTO peliculaDTO){
        PeliculaDTO result = this.peliculaService.save(peliculaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }
    @DeleteMapping("/{id}") //7
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        this.peliculaService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
    @PutMapping("/{id}") //7
    public ResponseEntity<PeliculaDTO> update(@PathVariable Long id, @RequestBody PeliculaDTO pelicula) {
        PeliculaDTO result = this.peliculaService.update(id, pelicula);
        return ResponseEntity.ok().body(result);
    }
    @GetMapping("/{id}") //4
    public ResponseEntity<PeliculaDTO> getDetailsById(@PathVariable Long id) {
        PeliculaDTO peliculaDTO = this.peliculaService.getDetailsById(id);
        return ResponseEntity.ok(peliculaDTO);
    }
    @GetMapping //5
    public ResponseEntity<List<PeliculaBasicDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String genre,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {
        List<PeliculaBasicDTO> peliculas = this.peliculaService.getByFilters(name, genre, order);
        return ResponseEntity.ok(peliculas);
    }
}
