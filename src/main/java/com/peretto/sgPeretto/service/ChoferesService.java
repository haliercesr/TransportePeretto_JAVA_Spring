package com.peretto.sgPeretto.service;


import com.peretto.sgPeretto.entity.Choferes;
import com.peretto.sgPeretto.repository.ChoferesRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChoferesService implements BaseService<Choferes, Long>{

    private final ChoferesRepository choferRepository;

    @Autowired
    public ChoferesService(ChoferesRepository choferRepository) {
        this.choferRepository = choferRepository;
    }

    public Choferes findOrCreateChofer(Choferes choferInfo) {
        if (choferInfo.getId() == null) {
            return choferRepository.save(choferInfo);
        } else {
            return choferRepository.findById(choferInfo.getId()).orElseGet(() -> choferRepository.save(choferInfo));
        }
    }

    @Override
    public List<Choferes> findAll() throws Exception {
        try {
            return choferRepository.findAll();
        } catch (Exception e){
            throw new Exception("Error al listar los choferes " + e.getMessage());
        }
    }

    @Override
    public Choferes findById(Long id) throws Exception {
        try {
            return choferRepository.findById(id).orElseThrow(() -> new Exception("Chofer no encontrado"));
        } catch (Exception e) {
            throw new Exception("Error al obtener el chofer " + e.getMessage());
        }
    }

    @Override
    public Choferes save(Choferes entity) throws Exception {
        try {
            return choferRepository.save(entity);
        } catch (Exception e){
            throw new Exception("Error al guardar el chofer " + e.getMessage());
        }
    }

    @Override
    public Choferes update(Long id, Choferes entity) throws Exception {
        try {
            Choferes chofer = choferRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Chofer no encontrado"));

            chofer.setNombreApellido(entity.getNombreApellido());
            chofer.setTelefono(entity.getTelefono());
            chofer.setDireccion(entity.getDireccion());
            chofer.setEmail(entity.getEmail());

            return choferRepository.save(chofer);
        } catch (Exception e) {
            throw new Exception("Error al actualizar los datos del chofer " + e.getMessage());
        }
    }

    @Override
    public boolean delete(Long id) throws Exception {
        try {
            if (choferRepository.existsById(id)){
                choferRepository.deleteById(id);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }
}
