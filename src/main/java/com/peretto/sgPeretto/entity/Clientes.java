package com.peretto.sgPeretto.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "Clientes")
public class Clientes extends Usuario{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @OneToMany(mappedBy = "clientes", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonManagedReference("cliente-clientesViajes")
    private List<Clientes_Viajes> clientesViajes;

//    @OneToMany(mappedBy = "clientesAlquiler", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//    @JsonManagedReference("cliente-maquinariasAquiler")
//    private List<MaquinariasAlquiler> maquinariasAlquilers;

//    @NonNull
//    @JsonManagedReference
//    @OneToMany(mappedBy = "cliente", fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<MaquinariasAlquiler> maquinariasAlquilers;
}
