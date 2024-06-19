package com.peretto.sgPeretto.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "maquinariaAlquiler")
public class MaquinariasAquiler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMaquinaria")
    private Long idMaquinaria;

    @Column(name = "tipoMaquina")
    private String tipoMaquina;

    @Column(name = "fecha")
    private Date fecha;

    @Column(name = "horas")
    private int horas;

    @Column(name = "precioNeto")
    private double precioNeto;

    @Column(name = "iva")
    private double iva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idCliente")
    @JsonBackReference("cliente-maquinariasAquiler")
    private Clientes cliente;
}
