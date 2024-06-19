package com.peretto.sgPeretto.service;

import com.peretto.sgPeretto.entity.Maquinarias;
import com.peretto.sgPeretto.repository.MaquinariaRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MaquinariaService implements BaseService<Maquinarias, Long> {

    private final MaquinariaRepository maquinariaRepository;

    @Autowired
    public MaquinariaService(MaquinariaRepository maquinariaRepository) {
        this.maquinariaRepository = maquinariaRepository;
    }

    @Override
    public List<Maquinarias> findAll() throws Exception {
        try {
            return maquinariaRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Error al listar las maquinarias " + e.getMessage());
        }
    }

    @Override
    public Maquinarias findById(Long id) throws Exception {
        try {
            return maquinariaRepository.findById(id).orElseThrow(() -> new Exception("Maquinaria no encontrada"));
        } catch (Exception e) {
            throw new Exception("Error al encontrar la maquinaria " + e.getMessage());
        }
    }

    @Override
    public Maquinarias save(Maquinarias entity) throws Exception {
        try {
            return maquinariaRepository.save(entity);
        } catch (Exception e) {
            throw new Exception("Error al guardar la maquinaria "+ e.getMessage());
        }
    }

    @Override
    public Maquinarias update(Long id, Maquinarias entity) throws Exception {
        try {
            Maquinarias maquinaria = maquinariaRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Maquinaria no encontrada"));

            maquinaria.setTipoMaquinaria(entity.getTipoMaquinaria());
            maquinaria.setDescripcion(entity.getDescripcion());

            return maquinariaRepository.save(maquinaria);
        } catch (Exception e) {
            throw new Exception("Error al actualizar la maquinaria " + e.getMessage());
        }
    }

    @Override
    public boolean delete(Long id) throws Exception {
        try {
            if (maquinariaRepository.existsById(id)){
                maquinariaRepository.deleteById(id);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception("Error al eliminar la maquinaria " + e.getMessage());
        }
    }
}
