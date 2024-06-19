package com.peretto.sgPeretto.repository;

import com.peretto.sgPeretto.entity.MaquinariasAlquiler;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

//El repository funciona como intermediario entre la base de datos y la lógica de negocios
//JpaRepository ayuda a simplificar la conexión con la base porque es una clase genérica
// Si hacen clic + ctrl sobre "Jpa..." les muestra los métodos y dentro de <> va: <entidad, tipoId de la entidad>
@Repository
public interface MaquinariasAlquilerRepository extends JpaRepository<MaquinariasAlquiler, Long> {
}
