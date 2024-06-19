package com.peretto.sgPeretto.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ChoferResponse {

    private Long id;
    private String nombreApellido;
    private Long telefono;
    private String direccion;
    private String email;
    private String cedulaId;



}
