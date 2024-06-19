package com.peretto.sgPeretto.controller;

import com.peretto.sgPeretto.dto.ActualizarAlquilerDTO;
import com.peretto.sgPeretto.dto.CrearAlquilerDTO;
import com.peretto.sgPeretto.service.MaquinariasAlquilerService;
import jakarta.validation.Valid;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@Validated
// Acá después hay que cambiar, porque está que cualquiera tenga permisos para modificar (*) y debería ser solo con usuario verificado
@CrossOrigin(origins = "*")
@RequestMapping(path = "api/v1/alquileres")
public class MaquinariasAlquilerController {
    private MaquinariasAlquilerService maquinariasAlquilerService;

    @Autowired
    public MaquinariasAlquilerController(MaquinariasAlquilerService maquinariasAlquilerService) {
        this.maquinariasAlquilerService = maquinariasAlquilerService;
    }

    @GetMapping("")
    public ResponseEntity<?> getAll(){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(maquinariasAlquilerService.findAll());
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al obtener el listado de alquileres " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    // Con pathVariable indico que se va a modificar ese valor
    public ResponseEntity<?> getOne(@PathVariable Long id){
        try {
            return ResponseEntity.status(HttpStatus.OK).body(maquinariasAlquilerService.findById(id));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alquileres no encontrado");
        }
    }

    @PostMapping
    // Pongo @RequestBody porque indico que le voy a pasar por body el contenido que debe almacenar en la base
    public ResponseEntity<?> save(@Valid @RequestBody CrearAlquilerDTO crearAlquilerDTO){
        try {
            return ResponseEntity.status(HttpStatus.CREATED).body(maquinariasAlquilerService.createAlquiler(crearAlquilerDTO));
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al guardar el alquiler " + e.getMessage());
        }
    }

    @PutMapping("/{idAlquiler}")
    public ResponseEntity<?> update(@PathVariable Long idAlquiler, @RequestParam Long idCliente, @RequestParam Long idMaquinaria, @Valid @RequestBody ActualizarAlquilerDTO actualizarAlquilerDTO){
        try{
            return ResponseEntity.status(HttpStatus.OK).body(maquinariasAlquilerService.updateAlquiler(idCliente, idMaquinaria, idAlquiler, actualizarAlquilerDTO));
        } catch (ResourceNotFoundException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No encontrado " + e.getMessage());
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Error al actualizar el alquiler " + e.getMessage());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable Long id){
        try {
            if (maquinariasAlquilerService.delete(id)){
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alquiler no encontrado");
            }
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al eliminar el alquiler de maquinaria " + e.getMessage());
        }
    }
}
