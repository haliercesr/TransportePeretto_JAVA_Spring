package com.peretto.sgPeretto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ViajesRequest {
    private ViajesResponse viaje;
    private ClienteRequest cliente;
    private ChoferRequest chofer;

    private List<GastosViajesRequest> gastosViajes;

}