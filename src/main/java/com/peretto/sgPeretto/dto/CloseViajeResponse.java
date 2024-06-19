package com.peretto.sgPeretto.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor

public class CloseViajeResponse {
    private double diferencia;
    private String mensaje;

    public CloseViajeResponse(double diferencia, String mensaje) {
        this.diferencia = diferencia;
        this.mensaje = mensaje;
    }

    public double getDiferencia() {
        return diferencia;
    }

    public void setDiferencia(double diferencia) {
        this.diferencia = diferencia;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }



}
