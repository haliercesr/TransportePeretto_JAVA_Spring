package com.peretto.sgPeretto.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "viaje")

public class Viajes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idViajes")
    private Long idViaje;

    @Column(name = "origenViajes")
    private String origenViajes;

    @Column(name = "paradasIntermedias")
    private String paradasIntermedias;

    @Column(name = "destinoViaje")
    private String destinoViaje;

    @Column(name = "Km")
    private String Km;

    @Column(name = "fechaInicioViaje")
    private Date fechaInicioViaje;

    @Column(name = "fechaFinViaje")
    private Date fechaFinViaje;

    @Column(name = "isViajeRealizado")
    private boolean isViajeRealizado;

    @Column(name = "montoOtorgado")
    private int montoOtorgado;


    @Column(name = "precioNetoViaje")
    private int precioNetoViaje;

    @Column(name = "IVA")
    private double Iva;

    @Column(name = "isPagoCompletado")
    private boolean isPagoCompletado;

    @Column(name = "isArchivado")
    private boolean isArchivado;

    @OneToMany(mappedBy = "viajesClientes", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("viaje-clientesViajes")
    private List<Clientes_Viajes> clientes_viajes = new ArrayList<>();

    @OneToMany(mappedBy = "viajesChofer", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("viaje-choferesViajes")
    private List<Choferes_Viajes> choferes_viajes = new ArrayList<>();

@OneToMany(mappedBy = "viaje", fetch= FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true )

        private List<GastosViajes> gastosViajes = new ArrayList<>();
}



