package com.peretto.sgPeretto.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
@MappedSuperclass
public abstract class Usuario {

    // Agrego validaciones:
    // Uso NonNull porque genera el constructor automáticamente pero no verifica si la cadena está vacía o en blanco por eso uso NotBlank
    // Lombok: genera el constructor y verifica que el campo no esté vacío cuando se crea el objeto o llama al método
    @NonNull
    // Verifica que el campo no sea nulo o espacios en blanco
    @NotBlank(message = "El nombre y apellido no puede estar vacío")
    // Tamaño mínimo de caracteres (se puede poner máximo y otros)
    @Size(min = 3, message = "El nombre y apellido debe tener más caracteres")
    @Column(name = "nombreApellido", nullable = false)
    private String nombreApellido;

    @NonNull
    @NotBlank(message = "El teléfono no puede estar vacío")
    // Corrobora que sean números y que tenga una longitud entre 7 y 25
    @Pattern(regexp = "^\\+?[0-9. ()-]{7,25}$", message = "El teléfono tiene un formato inválido")
    @Column(name = "telefono", nullable = false)
    private String telefono;

    @NonNull
    @NotBlank(message = "La dirección no puede estar vacía")
    @Size(min = 5, message = "La dirección debe tener al menos 5 caracteres")
    @Column(name = "direccion", nullable = false)
    private String direccion;

    @NonNull
    @NotBlank(message = "El email no puede estar vacío")
    // Validación para formato de email
    @Email(message = "El email no tiene un formato válido")
    @Column(name = "email", nullable = false, unique = true)
    private String email;

    @Column(name = "cedula_id", unique = true)
    private String cedulaId;
}
