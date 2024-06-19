package com.peretto.sgPeretto.repository;

import com.peretto.sgPeretto.entity.Choferes_Viajes;
import com.peretto.sgPeretto.entity.Viajes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ChoferesViajesRepository extends JpaRepository<Choferes_Viajes, Long> {

    @Query("SELECT cv.viajesChofer FROM Choferes_Viajes cv WHERE cv.choferes.id = :choferId")
    List<Viajes> findViajesByChoferId(@Param("choferId") Long choferId);


}
