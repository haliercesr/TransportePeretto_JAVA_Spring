package com.peretto.sgPeretto.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "gastos_viaje")

public class GastosViajes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idGastosViaje")
    private Long idGastosViaje;
    @Column(name = "gastosPeajes")
    private double gastosPeajes;

    @Column(name = "gastosCombustible")
    private double gastosCombustible;

    @Column(name = "gastosExtras")
    private double gastosExtras;

    @Column(name = "detalleGastosExtra")
    private String detalleGastosExtra;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idViaje")
    @JsonBackReference
    private Viajes viaje;


}
