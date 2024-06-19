package com.peretto.sgPeretto.controller;

import com.peretto.sgPeretto.entity.Choferes;
import com.peretto.sgPeretto.entity.Clientes;
import com.peretto.sgPeretto.service.ChoferesService;
import com.peretto.sgPeretto.service.ClienteService;
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
@RequestMapping(path = "api/v1/choferes")
public class ChoferController {
    private ChoferesService choferesService;

    @Autowired
    public ChoferController(ChoferesService choferesService) {
        this.choferesService = choferesService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(choferesService.findAll());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al listar los choferes " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(choferesService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Chofer no encontrado");
        }
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody Choferes entity){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(choferesService.save(entity));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al guardar el cliente " + e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody Choferes entity){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(choferesService.update(id, entity));
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No encontrado " + e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar el chofer " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            if(choferesService.delete(id)){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Chofer no encontrado");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el chofer " + e.getMessage());
        }
    }
}
