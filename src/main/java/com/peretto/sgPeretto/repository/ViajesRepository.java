package com.peretto.sgPeretto.repository;


import com.peretto.sgPeretto.entity.Viajes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ViajesRepository extends JpaRepository<Viajes, Long> {


}
