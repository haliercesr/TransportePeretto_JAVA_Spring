package com.peretto.sgPeretto.service;

import com.peretto.sgPeretto.entity.Clientes;
import com.peretto.sgPeretto.repository.ClienteRepository;
import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClienteService implements BaseService<Clientes,Long> {

    private final ClienteRepository clienteRepository;

    @Autowired
    public ClienteService(ClienteRepository clienteRepository) {
        this.clienteRepository = clienteRepository;
    }

    @Override
    public List<Clientes> findAll() throws Exception {
        try {
            return clienteRepository.findAll();
        } catch (Exception e){
            throw new Exception("Error al listar los clientes " + e.getMessage());
        }
    }

    @Override
    public Clientes findById(Long id) throws Exception {
        try {
           return clienteRepository.findById(id).orElseThrow(() -> new Exception("Cliente no encontrado"));
        } catch (Exception e) {
            throw new Exception("Error al obtener el cliente " + e.getMessage());
        }
    }

    @Override
    public Clientes save(Clientes entity) throws Exception {
        try {
            return clienteRepository.save(entity);
        } catch (Exception e){
            throw new Exception("Error al guardar el cliente " + e.getMessage());
        }
    }

    @Override
    public Clientes update(Long id, Clientes entity) throws Exception {
        try {
            Clientes cliente = clienteRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Cliente no encontrado"));

            cliente.setNombreApellido(entity.getNombreApellido());
            cliente.setTelefono(entity.getTelefono());
            cliente.setDireccion(entity.getDireccion());
            cliente.setEmail(entity.getEmail());

            return clienteRepository.save(cliente);
        } catch (Exception e) {
            throw new Exception("Error al actualizar los datos del cliente " + e.getMessage());
        }
    }

    @Override
    public boolean delete(Long id) throws Exception {
        try {
            if (clienteRepository.existsById(id)){
                clienteRepository.deleteById(id);
                return true;
            } else {
                throw new Exception();
            }
        } catch (Exception e) {
            throw new Exception(e.getMessage());
        }
    }

    public Clientes findOrCreateCliente(Clientes clienteInfo) {
        if (clienteInfo.getId() == null) {
            return clienteRepository.save(clienteInfo);
        } else {
            return clienteRepository.findById(clienteInfo.getId()).orElseGet(() -> clienteRepository.save(clienteInfo));
        }
    }
}
