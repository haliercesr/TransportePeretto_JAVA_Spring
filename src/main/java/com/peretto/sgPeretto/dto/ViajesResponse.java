package com.peretto.sgPeretto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ViajesResponse {

    private Long idViaje;
    private String origenViajes;
    private String paradasIntermedias;
    private String destinoViaje;
    private Date fechaInicioViaje;
    private Date fechaFinViaje;
    private int montoOtorgado;
    private double gastoPeajes;
    private double gastoCombustible;
    private double gastosExtras;
    private String detallesGastosExtras;
    private int precioNetoViaje;
    private boolean viajeRealizado;
    private double IVA;
    private boolean pagoCompletado;
    private boolean archivado;
    private ClienteResponse cliente;
    private ChoferResponse chofer;
    private List<GastosViajesResponse> gastosViaje;

}
