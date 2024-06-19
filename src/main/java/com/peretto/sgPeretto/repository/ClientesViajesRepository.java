package com.peretto.sgPeretto.repository;

import com.peretto.sgPeretto.entity.Clientes_Viajes;
import com.peretto.sgPeretto.entity.Viajes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientesViajesRepository extends JpaRepository<Clientes_Viajes, Long> {

    @Query("SELECT cv.viajesClientes FROM Clientes_Viajes cv WHERE cv.clientes.id = :clienteId")
    List<Viajes> findViajesClienteId(@Param("clienteId") Long clienteId);
}
