package com.peretto.sgPeretto.repository;

import com.peretto.sgPeretto.entity.Clientes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Clientes, Long> {
    Optional<Clientes> findById(Long id);
}
