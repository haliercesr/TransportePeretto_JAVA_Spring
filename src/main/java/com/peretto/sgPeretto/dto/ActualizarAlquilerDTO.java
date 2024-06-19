package com.peretto.sgPeretto.dto;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.time.LocalDate;

@Data
public class ActualizarAlquilerDTO {
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
    private Long idCliente;

    //Maquinaria
    @NotNull
    private Long idMaquinaria;
}
