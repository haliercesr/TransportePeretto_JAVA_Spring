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
@Table(name = "choferesViajes")
public class Choferes_Viajes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long idChoferesViajes;

    //relación con choferes
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idChofer")
    @JsonBackReference("chofer-choferesViajes")
    private Choferes choferes;

    //relación con viajes
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idViajes")
    @JsonBackReference("viaje-choferesViajes")
    private Viajes viajesChofer;
}
