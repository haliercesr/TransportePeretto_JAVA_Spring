package com.peretto.sgPeretto.service;

import com.peretto.sgPeretto.dto.ActualizarAlquilerDTO;
import com.peretto.sgPeretto.dto.CrearAlquilerDTO;
import com.peretto.sgPeretto.entity.Clientes;
import com.peretto.sgPeretto.entity.Maquinarias;
import com.peretto.sgPeretto.entity.MaquinariasAlquiler;
import com.peretto.sgPeretto.repository.ClienteRepository;
import com.peretto.sgPeretto.repository.MaquinariaRepository;
import com.peretto.sgPeretto.repository.MaquinariasAlquilerRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class MaquinariasAlquilerService implements BaseService<MaquinariasAlquiler, Long> {

    // Se colocan los repositorios que van a interactuar con la base de datos y el constructor
    private final MaquinariasAlquilerRepository maquinariasAlquilerRepository;
    private final ClienteRepository clienteRepository;
    private final MaquinariaRepository maquinariaRepository;

    @Autowired
    public MaquinariasAlquilerService(MaquinariasAlquilerRepository maquinariasAlquilerRepository, ClienteRepository clienteRepository, MaquinariaRepository maquinariaRepository) {
        this.maquinariasAlquilerRepository = maquinariasAlquilerRepository;
        this.clienteRepository = clienteRepository;
        this.maquinariaRepository = maquinariaRepository;
    }

    @Override
    public List<MaquinariasAlquiler> findAll() throws Exception {
        try{
            return maquinariasAlquilerRepository.findAll();
        }catch (Exception e){
            throw new Exception("Error al obtener el listado de alquiler de máquinas" + e.getMessage(), e);
        }
    }

    @Override
    public MaquinariasAlquiler findById(Long id) throws Exception {
        try{
            return maquinariasAlquilerRepository.findById(id).orElseThrow(() -> new Exception("Alquiler de maquinaria no encontrado"));
        }catch (Exception e){
            throw new Exception("Error al obtener los datos de alquiler de maquinaria " + e.getMessage(), e);
        }
    }

    @Override
    public MaquinariasAlquiler save(MaquinariasAlquiler entity) throws Exception {
        try{
            return maquinariasAlquilerRepository.save(entity);
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    @Override
    public MaquinariasAlquiler update(Long id, MaquinariasAlquiler entity) throws Exception {
        try{
            Optional<MaquinariasAlquiler> entityOptional = maquinariasAlquilerRepository.findById(id);
            MaquinariasAlquiler maquinariasAlquiler = entityOptional.get();
            maquinariasAlquiler = maquinariasAlquilerRepository.save(maquinariasAlquiler);
            return maquinariasAlquiler;
        } catch (Exception e){
            throw new Exception(e.getMessage());
        }
    }

    @Override
    @Transactional
    public boolean delete(Long id) throws Exception {
        try {
            if(!maquinariasAlquilerRepository.existsById(id)){
                throw new Exception("No se encontró el alquiler");
            }
            maquinariasAlquilerRepository.deleteById(id);
            return true;
        } catch (Exception e){
            throw new Exception("Error al eliminar el alquiler de maquinaria"+ e.getMessage(), e);
        }
    }

    // Las transacciones son para los métodos que modifican el estado de la base de datos
    @Transactional
    public MaquinariasAlquiler createAlquiler(CrearAlquilerDTO crearAlquilerDTO) throws Exception {
        try {
            Clientes cliente;

            // Verifico si el cliente existe o no para crearlo
            if (crearAlquilerDTO.getIdCliente() != null){
                cliente = clienteRepository.findById(crearAlquilerDTO.getIdCliente())
                        .orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));
            } else {

                if( crearAlquilerDTO.getNombreCliente() == null || crearAlquilerDTO.getNombreCliente().isEmpty() ||
                    crearAlquilerDTO.getTelefonoCliente() == null || crearAlquilerDTO.getTelefonoCliente().isEmpty()) {
                    throw new IllegalArgumentException("Los datos del cliente no pueden estar vacios");
                }
                cliente = new Clientes();
                cliente.setNombreApellido(crearAlquilerDTO.getNombreCliente());
                cliente.setTelefono(crearAlquilerDTO.getTelefonoCliente());
                cliente.setEmail(crearAlquilerDTO.getEmailCliente());
                cliente.setDireccion(crearAlquilerDTO.getDireccionCliente());

                cliente = clienteRepository.save(cliente);
            }

            // Verifico que la maquinaria exista
            Maquinarias maquinaria = maquinariaRepository.findById(crearAlquilerDTO.getIdMaquinaria())
                    .orElseThrow(() -> new ResourceNotFoundException("Maquinaria no encontrada"));

            //Creo el alquiler
            MaquinariasAlquiler alquiler = new MaquinariasAlquiler();
            alquiler.setClientesAlquiler(cliente);
            alquiler.setMaquinarias(maquinaria);
            alquiler.setFechaAlquiler(crearAlquilerDTO.getFechaAlquiler());
            alquiler.setFechaDevolucion(crearAlquilerDTO.getFechaDevolucion());
            alquiler.setHoras(crearAlquilerDTO.getHoras());
            alquiler.setPrecioNeto(crearAlquilerDTO.getPrecioNeto());
            alquiler.setIva(crearAlquilerDTO.getIva());
            alquiler.setPrecioFinal(crearAlquilerDTO.getPrecioFinal());

            return maquinariasAlquilerRepository.save(alquiler);

        } catch (Exception e){
            throw new Exception("Error al guardar el alquiler de la maquinaria " + e.getMessage(), e);
        }
    }


    @Transactional
    public MaquinariasAlquiler updateAlquiler(Long idCliente, Long idMaquinaria, Long idAlquiler,ActualizarAlquilerDTO actualizarAlquilerDTO) throws Exception {
        try {
            // Verifico que exista el alquiler en la base
            MaquinariasAlquiler alquiler = maquinariasAlquilerRepository.findById(idAlquiler).orElseThrow(() -> new ResourceNotFoundException("No se encontró el alquiler"));

            // Actualizo los datos del alquiler
            alquiler.setFechaAlquiler(actualizarAlquilerDTO.getFechaAlquiler());
            alquiler.setFechaDevolucion(actualizarAlquilerDTO.getFechaDevolucion());
            alquiler.setHoras(actualizarAlquilerDTO.getHoras());
            alquiler.setPrecioNeto(actualizarAlquilerDTO.getPrecioNeto());
            alquiler.setIva(actualizarAlquilerDTO.getIva());
            alquiler.setPrecioFinal(actualizarAlquilerDTO.getPrecioFinal());
            alquiler.setClientesAlquiler(clienteRepository.findById(idCliente).orElseThrow(() -> new ResourceNotFoundException("No se encontró el cliente")));
            alquiler.setMaquinarias(maquinariaRepository.findById(idMaquinaria).orElseThrow(() -> new ResourceNotFoundException("No se encontró la maquinaria")));

            return maquinariasAlquilerRepository.save(alquiler);

        } catch (Exception e){
            throw new Exception( "Error al actualizar los datos de alquiler de maquinaria " + e.getMessage(), e);
        }
    }



}
