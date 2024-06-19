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
@Table(name = "clientesViajes")
public class Clientes_Viajes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")

    private Long idClientesViajes;

    //relación con clientes
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idClientes")
    @JsonBackReference("cliente-clientesViajes")
    private Clientes clientes;

    //relación con viajes
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idViajes")
    @JsonBackReference("viaje-clientesViajes")
    private Viajes viajesClientes;
}
