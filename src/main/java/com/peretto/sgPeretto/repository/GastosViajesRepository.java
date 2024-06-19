package com.peretto.sgPeretto.repository;

import com.peretto.sgPeretto.entity.GastosViajes;
import com.peretto.sgPeretto.entity.Viajes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface GastosViajesRepository  extends JpaRepository<GastosViajes, Long> {

    Optional<GastosViajes> findByViaje(Viajes viaje);
}
