package com.peretto.sgPeretto.service;


import com.peretto.sgPeretto.dto.*;
import com.peretto.sgPeretto.entity.*;
import com.peretto.sgPeretto.exception.ResourceNotFoundException;
import com.peretto.sgPeretto.repository.*;
import com.peretto.sgPeretto.utilitys.mapper.ChofferMapper;
import com.peretto.sgPeretto.utilitys.mapper.ClienteMapper;
import com.peretto.sgPeretto.utilitys.mapper.ViajesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ViajesService implements BaseService<Viajes, Long> {

    private final ViajesRepository viajesRepository;

    private final ClienteRepository clientesRepository;

    private final ChoferesRepository choferesRepository;

    private final ClientesViajesRepository clientesViajesRepository;

    private final ChoferesViajesRepository choferesViajesRepository;

    @Autowired
    public ViajesService(ViajesRepository viajesRepository, ClienteRepository clientesRepository, ChoferesRepository choferesRepository, ClientesViajesRepository clientesViajesRepository, ChoferesViajesRepository choferesViajesRepository) {
        this.viajesRepository = viajesRepository;
        this.clientesRepository = clientesRepository;
        this.choferesRepository = choferesRepository;
        this.clientesViajesRepository = clientesViajesRepository;
        this.choferesViajesRepository = choferesViajesRepository;
    }

    @Override
    public List<Viajes> findAll() throws Exception {
        try {
            return viajesRepository.findAll();
        } catch (Exception e) {
            throw new Exception("Error al listar los viajes " + e.getMessage());
        }
    }

    @Override
    public Viajes findById(Long id) throws Exception {
        return viajesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Viaje no encontrado"));
    }

    @Override
    public Viajes save(Viajes viaje) throws Exception {
        try {
            return viajesRepository.save(viaje);
        } catch (Exception e) {
            throw new Exception("Error al guardar el viaje " + e.getMessage());
        }
    }

    @Override
    public Viajes update(Long id, Viajes viaje) {
        Viajes existingViaje = viajesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Viaje no encontrado"));

        existingViaje.setOrigenViajes(viaje.getOrigenViajes());
        existingViaje.setDestinoViaje(viaje.getDestinoViaje());
        existingViaje.setFechaInicioViaje(viaje.getFechaInicioViaje());
        existingViaje.setMontoOtorgado(viaje.getMontoOtorgado());
        existingViaje.setPrecioNetoViaje(viaje.getPrecioNetoViaje());

        return viajesRepository.save(existingViaje);
    }

    @Override
    public boolean delete(Long id) {
        if (viajesRepository.existsById(id)) {
            viajesRepository.deleteById(id);
            return true;
        } else {
            throw new ResourceNotFoundException("Viaje no encontrada");
        }
    }


    public ViajesResponse createViajeWithClienteAndChofer(ViajesRequest request) {

        Viajes viajeEntity = ViajesMapper.INSTANCE.toEntity(request.getViaje());
        Clientes clienteEntity = ViajesMapper.INSTANCE.toEntity(request.getCliente());
        Choferes choferEntity = ViajesMapper.INSTANCE.toEntity(request.getChofer());

        Clientes cliente = clientesRepository.findById(clienteEntity.getId())
                .orElseGet(() -> clientesRepository.save(clienteEntity));

        Choferes chofer = choferesRepository.findByCedulaId(choferEntity.getCedulaId())
                .orElseGet(() -> choferesRepository.save(choferEntity));

        Clientes_Viajes clientesViajes = new Clientes_Viajes();
        clientesViajes.setViajesClientes(viajeEntity);
        clientesViajes.setClientes(cliente);
        viajeEntity.getClientes_viajes().add(clientesViajes);

        Choferes_Viajes choferesViajes = new Choferes_Viajes();
        choferesViajes.setViajesChofer(viajeEntity);
        choferesViajes.setChoferes(chofer);
        viajeEntity.getChoferes_viajes().add(choferesViajes);

        Viajes savedViaje = viajesRepository.save(viajeEntity);

        ViajesResponse response = ViajesMapper.INSTANCE.toResponse(savedViaje);
        response.setCliente(ClienteMapper.INSTANCE.toDto(cliente));
        response.setChofer(ChofferMapper.INSTANCE.toDto(chofer));

        return response;
    }


    public ViajesResponse updateViajeWithClienteAndChofer(Long id, ViajesUpdateRequest updateRequest) {
        if (updateRequest.getCliente() == null) {
            throw new IllegalArgumentException("ClienteRequest no puede ser null");
        }
        if (updateRequest.getChofer() == null) {
            throw new IllegalArgumentException("ChoferRequest no puede ser null");
        }
        Viajes viaje = viajesRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Viaje no encontrado"));

        Clientes cliente = null;
        if (updateRequest.getCliente() != null) {
            cliente = clientesRepository.findById(updateRequest.getCliente().getId())
                    .orElseGet(() -> clientesRepository.save(ClienteMapper.INSTANCE.toEntity(updateRequest.getCliente())));
        }
        Choferes chofer = null;
        if (updateRequest.getChofer() != null) {
            chofer = choferesRepository.findById(updateRequest.getChofer().getId())
                    .orElseGet(() -> choferesRepository.save(ChofferMapper.INSTANCE.toEntity(updateRequest.getChofer())));
        }
        viaje.setOrigenViajes(updateRequest.getOrigenViajes());

        viaje.setDestinoViaje(updateRequest.getDestinoViaje());
        viaje.setFechaInicioViaje(updateRequest.getFechaInicioViaje());
        viaje.setMontoOtorgado(updateRequest.getMontoOtorgado());
        viaje.setPrecioNetoViaje(updateRequest.getPrecioNetoViaje());

        Viajes savedViaje = viajesRepository.save(viaje);

        Clientes_Viajes clientesViajes = new Clientes_Viajes();
        clientesViajes.setViajesClientes(savedViaje);
        clientesViajes.setClientes(cliente);
        savedViaje.getClientes_viajes().add(clientesViajes);

        Choferes_Viajes choferesViajes = new Choferes_Viajes();
        choferesViajes.setViajesChofer(savedViaje);
        choferesViajes.setChoferes(chofer);
        savedViaje.getChoferes_viajes().add(choferesViajes);

        viajesRepository.save(savedViaje);

        ViajesResponse response = ViajesMapper.INSTANCE.toResponse(savedViaje);
        response.setCliente(ClienteMapper.INSTANCE.toDto(cliente));
        response.setChofer(ChofferMapper.INSTANCE.toDto(chofer));

        return response;
    }

    public Viajes addClienteToViaje(Long viajeId, Long clienteId, Clientes clienteInfo) {

        Optional<Viajes> optionalViajes = viajesRepository.findById(viajeId);
        if (optionalViajes.isPresent()) {
            Viajes viajes = optionalViajes.get();
            Clientes cliente = clientesRepository.findById(clienteId).orElse(clienteInfo);
            if (cliente.getId() == null) {
                cliente = clientesRepository.save(cliente);
            }
            Clientes_Viajes clientesViajes = new Clientes_Viajes();
            clientesViajes.setViajesClientes((viajes));
            clientesViajes.setClientes(cliente);
            viajes.getClientes_viajes().add(clientesViajes);
            clientesViajesRepository.save(clientesViajes);
        }
        return null;
    }


    public Viajes addChoferToViaje(Long viajeId, Long choferId, Choferes choferInfo) {
        Viajes viaje = viajesRepository.findById(viajeId)
                .orElseThrow(() -> new ResourceNotFoundException("Viaje no encontrado"));
        Choferes chofer = choferesRepository.findById(choferId)
                .orElse(choferInfo);
        if (chofer.getId() == null) {
            chofer = choferesRepository.save(chofer);
        }
        Choferes_Viajes choferesViajes = new Choferes_Viajes();
        choferesViajes.setViajesChofer(viaje);
        choferesViajes.setChoferes(chofer);

        viaje.getChoferes_viajes().add(choferesViajes);
        choferesViajesRepository.save(choferesViajes);
        return viajesRepository.save(viaje);
    }

    public Viajes removeClienteFromViaje(Long viajeId, Long clienteId) {
        Viajes viaje = viajesRepository.findById(viajeId)
                .orElseThrow(() -> new ResourceNotFoundException("Viaje no encontrado"));

        viaje.getClientes_viajes().removeIf(cv -> cv.getClientes().getId().equals(clienteId));
        return viajesRepository.save(viaje);
    }


    public Viajes removeChoferesFormViaje(Long viajeId, Long choferId) {
        Viajes viaje = viajesRepository.findById(viajeId)
                .orElseThrow(() -> new ResourceNotFoundException("Viaje no encontrado"));

        viaje.getChoferes_viajes().removeIf(cv -> cv.getChoferes().getId().equals(choferId));
        return viajesRepository.save(viaje);
    }


    public List<Viajes> findViajesByClienteId(Long clienteId) {

        return clientesViajesRepository.findViajesClienteId(clienteId);

    }


    public List<Viajes> findViajesByChoferId(Long choferId) {

        return choferesViajesRepository.findViajesByChoferId((choferId));

    }

}
