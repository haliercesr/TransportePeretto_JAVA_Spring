package com.peretto.sgPeretto.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "maquinarias")
public class Maquinarias {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idMaquinaria")
    private Long idMaquinaria;

    @NonNull
    @Column(name = "tipoMaquina")
    private String tipoMaquinaria;

    @Column(name = "descripcion")
    private String descripcion;

//    @NonNull
//    @JsonBackReference
//    @OneToMany(mappedBy = "maquinarias", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<MaquinariasAlquiler> maquinariasAlquilers;
}
