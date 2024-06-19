package com.peretto.sgPeretto.controller;


import com.peretto.sgPeretto.dto.CloseViajeResponse;
import com.peretto.sgPeretto.dto.GastosViajesRequest;
import com.peretto.sgPeretto.dto.GastosViajesResponse;
import com.peretto.sgPeretto.exception.BadRequestException;
import com.peretto.sgPeretto.exception.ResourceNotFoundException;
import com.peretto.sgPeretto.service.GastosViajesServicies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/gastos")
public class GastosViajesController {

    @Autowired
    private GastosViajesServicies gastosViajesServicies;


    @PostMapping(value = "/viaje/{viajeId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createGastosViaje(@PathVariable Long viajeId, @RequestBody GastosViajesRequest request) {
        try {
            GastosViajesResponse response = gastosViajesServicies.createGastosViajes(viajeId, request);
            return ResponseEntity.ok(response);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateGastosViaje(@PathVariable Long id, @RequestBody GastosViajesRequest request) {
        try {
            GastosViajesResponse response = gastosViajesServicies.updateGastosViajes(id, request);
            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

//    @GetMapping(value = "/{id}", produces = "application/json")
//    public ResponseEntity<GastosViajesResponse> getGastoViajeById(@PathVariable Long id) {
//        GastosViajesResponse response = gastosViajesServicies.getGastoViajeId(id);
//        return ResponseEntity.ok(response);
//    }

    @GetMapping(value = "/viaje/{viajeId}", produces = "application/json")
    public ResponseEntity<?> getGastosByViajeId(@PathVariable Long viajeId) {
        try {
            List<GastosViajesResponse> responseList = gastosViajesServicies.getGastosByViajeId(viajeId);
            return ResponseEntity.ok(responseList);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @PostMapping(value = "/close/{viajeId}", produces = "application/json")
    public ResponseEntity<?> closeViaje(@PathVariable Long viajeId) {
        try {
            CloseViajeResponse response = gastosViajesServicies.closeViaje(viajeId);
            return ResponseEntity.ok(response);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }





    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteGastosViaje(@PathVariable Long id) {

        try {
            boolean deleted = gastosViajesServicies.deleteGastosViajes(id);
            if (deleted) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Gasto Eliminado con exito ");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Gasto no encontrado");
            }
        } catch (ResourceNotFoundException ex) {
            throw new ResourceNotFoundException("gasto no encontrado ");
        }
        catch (BadRequestException e) {
            throw new BadRequestException("La solicitud es incorrecta");
        }
        catch (Exception e) {
            throw new RuntimeException("Error interno del servidor: " + e.getMessage());
        }
    }




    }




