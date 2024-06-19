package com.peretto.sgPeretto.dto;

import java.util.Date;

import com.peretto.sgPeretto.entity.Viajes;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class ViajesUpdateRequest {
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
    private double Iva;
    private boolean pagoCompletado;
    private boolean archivado;
    private ClienteRequest cliente;
    private ChoferRequest chofer;


}
