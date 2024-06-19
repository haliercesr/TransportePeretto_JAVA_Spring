package com.peretto.sgPeretto.controller;

import com.peretto.sgPeretto.entity.Maquinarias;
import com.peretto.sgPeretto.service.MaquinariaService;
import jakarta.validation.Valid;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/maquinarias")
public class MaquinariaController {

    private MaquinariaService maquinariaService;

    @Autowired
    public MaquinariaController(MaquinariaService maquinariaService) {
        this.maquinariaService = maquinariaService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(maquinariaService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al listar las maquinarias " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(maquinariaService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Maquinaria no encontrada");
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Maquinarias entity){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(maquinariaService.save(entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al guardar el alquiler " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Maquinarias entity){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(maquinariaService.update(id, entity));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No encontrado " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar la maquinaria " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            if(maquinariaService.delete(id)){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Maquinaria no encontrada");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar la maquinaria " + e.getMessage());
        }
    }
}