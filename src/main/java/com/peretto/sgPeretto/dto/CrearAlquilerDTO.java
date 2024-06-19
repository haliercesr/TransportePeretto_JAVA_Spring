package com.peretto.sgPeretto.dto;

import com.peretto.sgPeretto.entity.Clientes;
import com.peretto.sgPeretto.entity.Maquinarias;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class CrearAlquilerDTO {
    @NotNull
    private LocalDate fechaAlquiler;
    @NotNull
    private LocalDate fechaDevolucion;
    @NotNull
    private int horas;
    @NotNull
    private double precioNeto;
    @NotNull
    private double iva;
    @NotNull
    private double precioFinal;

    // Cliente
    // puede ser null si no existe el cliente
    private Long idCliente;
    private String nombreCliente;
    private String telefonoCliente;
    private String direccionCliente;
    private String emailCliente;

    //Maquinaria
    @NotNull
    private Long idMaquinaria;
}
