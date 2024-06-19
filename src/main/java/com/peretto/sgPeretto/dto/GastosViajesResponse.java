package com.peretto.sgPeretto.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GastosViajesResponse {

    private Long idGastosViaje;
    private double gastosPeajes;
    private double gastosCombustible;
    private double gastosExtras;
    private String detalleGastosExtra;


}
