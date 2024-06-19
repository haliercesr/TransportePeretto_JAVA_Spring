package com.peretto.sgPeretto.controller;
import com.peretto.sgPeretto.dto.ViajesRequest;
import com.peretto.sgPeretto.dto.ViajesResponse;
import com.peretto.sgPeretto.dto.ViajesUpdateRequest;
import com.peretto.sgPeretto.entity.Choferes;
import com.peretto.sgPeretto.entity.Clientes;
import com.peretto.sgPeretto.entity.Viajes;
import com.peretto.sgPeretto.exception.ResourceNotFoundException;
import com.peretto.sgPeretto.service.ViajesService;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@Validated
@CrossOrigin(origins = "*")
@RequestMapping("/api/v1/viajes")
public class ViajesController {

    private ViajesService viajesService;

    public ViajesController(ViajesService viajesService) {
        this.viajesService = viajesService;
    }


    @GetMapping(produces = "application/json")
    public ResponseEntity<?> geAllViajes() {
        try {
            List<Viajes> viajes = viajesService.findAll();
            if (viajes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay data Disponible");
            } else {
                return ResponseEntity.ok(viajes);
            }
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron viajes");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }



    @GetMapping(value = "/{id}", produces = "application/json")
    public ResponseEntity<?> getViajeById(@Parameter(description = "ID de viaje ", required = true) @PathVariable Long id) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(viajesService.findById(id));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Viaje no encontrado");
        }
    }


    /**
     * Creates a new Viaje with associated Clientes and Choferes.
     *
     * @param request The request containing the Viaje details, Clientes ID, and Choferes ID.
     * @return A ResponseEntity containing the created Viaje.
     */

    @PostMapping(value = "/create", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> createViajeWithClienteAndChofer(@RequestBody ViajesRequest request) {
        try {
            ViajesResponse createdViaje = viajesService.createViajeWithClienteAndChofer(request);
            return  ResponseEntity.status(HttpStatus.CREATED).body(createdViaje);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @PutMapping(value = "/update/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> updateViajeWithClienteAndChofer(@PathVariable Long id, @RequestBody ViajesUpdateRequest updateRequest) {
        try {
            ViajesResponse updatedViaje = viajesService.updateViajeWithClienteAndChofer(id, updateRequest);
            return ResponseEntity.ok(updatedViaje);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<?> deleteViaje(@PathVariable Long id) {
        try {
            boolean deleted = viajesService.delete(id);
            if (deleted) {
                return ResponseEntity.status(HttpStatus.CREATED).body("Viaje Eliminado con exito ");
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Viaje no encontrado");
            }
        } catch (ResourceNotFoundException ex) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Viaje no encontrado");
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PostMapping(value = "/{viajeId}/clientes/{clienteId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addClienteToViaje(@PathVariable Long viajeId, @PathVariable Long clienteId, @RequestBody Clientes clienteInfo) {
        try {
            Viajes updatedViaje = viajesService.addClienteToViaje(viajeId, clienteId, clienteInfo);
            return ResponseEntity.ok(updatedViaje);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping(value = "/{viajeId}/choferes/{choferId}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<?> addChoferToViaje(@PathVariable Long viajeId, @PathVariable Long choferId, @RequestBody Choferes choferInfo) {
        try {
            Viajes updatedViaje = viajesService.addChoferToViaje(viajeId, choferId, choferInfo);
            return ResponseEntity.ok(updatedViaje);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @DeleteMapping(value = "/{viajeId}/clientes/{clienteId}")
    public ResponseEntity<?> removeClienteFromViaje(@PathVariable Long viajeId, @PathVariable Long clienteId) {
        try {
            Viajes updatedViaje = viajesService.removeClienteFromViaje(viajeId, clienteId);
            return ResponseEntity.ok(updatedViaje);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


    @DeleteMapping(value = "/{viajeId}/choferes/{choferId}")
    public ResponseEntity<?> removeChoferFromViaje(@PathVariable Long viajeId, @PathVariable Long choferId) {
        try {
            Viajes updatedViaje = viajesService.removeChoferesFormViaje(viajeId, choferId);
            return ResponseEntity.ok(updatedViaje);
        } catch (ResourceNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value = "/clientes/{clienteId}", produces = "application/json")
    public ResponseEntity<?> getViajesByClienteId(@PathVariable Long clienteId) {
        try {
            List<Viajes> viajes = viajesService.findViajesByClienteId(clienteId);
            if (viajes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron viajes para el cliente");
            } else {
                return ResponseEntity.ok(viajes);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @GetMapping(value = "/choferes/{choferId}", produces = "application/json")
    public ResponseEntity<?> getViajesByChoferId(@PathVariable Long choferId) {
        try {
            List<Viajes> viajes = viajesService.findViajesByChoferId(choferId);
            if (viajes.isEmpty()) {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron viajes para el chofer");
            } else {
                return ResponseEntity.ok(viajes);
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }
}