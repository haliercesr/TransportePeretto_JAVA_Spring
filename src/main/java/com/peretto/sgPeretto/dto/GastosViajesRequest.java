package com.peretto.sgPeretto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GastosViajesRequest {

    private Long idViaje;
    private double gastosPeajes;


    private double gastosCombustible;


    private double gastosExtras;


    private String detalleGastosExtra;

    private List<ViajesRequest> viajesRequests;

}
