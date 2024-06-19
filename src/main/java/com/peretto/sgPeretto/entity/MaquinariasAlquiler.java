package com.peretto.sgPeretto.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
@Table(name = "maquinariaAlquiler")
public class MaquinariasAlquiler {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idAlquiler")
    private Long idAlquiler;

    // Verifica que se ingresen fechas a partir de la actual
    //@FutureOrPresent(message = "La fecha debe ser en el presente o futuro")
    @Column(name = "fechaAlquiler", nullable = false)
    private LocalDate fechaAlquiler;

    @Column(name = "fechaDevolucion", nullable = false)
    private LocalDate fechaDevolucion;

    @Positive(message = "Las horas no pueden ser 0 o negativas")
    @Column(name = "horas", nullable = false)
    private int horas;

    // Verifica que no se ingresen valores menores a 0
    @Positive(message = "El precio no puede ser negativo")
    @Column(name = "precioNeto", nullable = false)
    private double precioNeto;

    // Verifica que no se ingresen valores menores a 0
    // Puede aplicar iva o no
    @PositiveOrZero(message = "El iva no puede ser negativo")
    @Column(name = "iva", nullable = false)
    private double iva;

    @Positive(message = "El precio final no puede ser negativo")
    @Column(name = "precioFinal", nullable = false)
    private double precioFinal;

    @ManyToOne
    @JoinColumn(name = "idCliente")
    //@JsonManagedReference
    private Clientes clientesAlquiler;

    @ManyToOne
    @JoinColumn(name = "idMaquinaria")
    //@JsonBackReference
    private Maquinarias maquinarias;
}
