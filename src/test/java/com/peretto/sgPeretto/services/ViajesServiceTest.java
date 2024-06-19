package com.peretto.sgPeretto.services;

import com.peretto.sgPeretto.entity.*;
import com.peretto.sgPeretto.service.ChoferesService;
import com.peretto.sgPeretto.service.ClienteService;
import com.peretto.sgPeretto.service.ViajesService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.peretto.sgPeretto.repository.ClientesViajesRepository;
import com.peretto.sgPeretto.repository.ChoferesViajesRepository;
import com.peretto.sgPeretto.repository.ViajesRepository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ViajesServiceTest {

    @Mock
    private ViajesRepository viajesRepository;

    @Mock
    private ClientesViajesRepository clientesViajesRepository;

    @Mock
    private ChoferesViajesRepository choferesViajesRepository;


    @Mock
    private ClienteService clienteService;

    @Mock
    private ChoferesService choferService;

    @InjectMocks
    private ViajesService viajesService;
    private Clientes cliente;
    private Choferes chofer;
    private Viajes viaje;


    @BeforeEach
    public void setup() {
        cliente = new Clientes();
        cliente.setId(1L);
        cliente.setCedulaId("12345678");

        chofer = new Choferes();
        chofer.setId(1L);
        chofer.setCedulaId("98765432");

        viaje = new Viajes();
        viaje.setIdViaje(1L);
//        viaje.setClientes_viajes("Sample Viaje");

        List<Clientes_Viajes> clientesViajes = new ArrayList<>();
        List<Choferes_Viajes> choferesViajes = new ArrayList<>();

        viaje.setClientes_viajes(clientesViajes);
        viaje.setChoferes_viajes(choferesViajes);
    }


    @Test
    public void testFindViajesByClienteId_whenClienteIdExists_thenReturnCorrectViajes() {
        // Given
        Long clienteId = 1L;
        List<Viajes> expectedViajes = Arrays.asList(new Viajes(), new Viajes());
        when(clientesViajesRepository.findViajesClienteId(clienteId)).thenReturn(expectedViajes);

        // When
        List<Viajes> actualViajes = viajesService.findViajesByClienteId(clienteId);

        // Then
        assertEquals(expectedViajes, actualViajes);
    }

    @Test
    public void testFindViajesByChoferId_whenChoferIdExists_thenReturnCorrectViajes() {
        // Given
        Long choferId = 1L;
        List<Viajes> expectedViajes = Arrays.asList(new Viajes(), new Viajes());
        when(choferesViajesRepository.findViajesByChoferId(choferId)).thenReturn(expectedViajes);

        // When
        List<Viajes> actualViajes = viajesService.findViajesByChoferId(choferId);

        // Then
        assertEquals(expectedViajes, actualViajes);
    }

    @Test
    public void testFindViajesByClienteId_whenClienteIdDoesNotExist_thenReturnEmptyList() {
        // Given
        Long clienteId = 1L;
        when(clientesViajesRepository.findViajesClienteId(clienteId)).thenReturn(new ArrayList<>());

        // When
        List<Viajes> actualViajes = viajesService.findViajesByClienteId(clienteId);

        // Then
        assertEquals(0, actualViajes.size());
    }

    @Test
    public void testFindViajesByChoferId_whenChoferIdDoesNotExist_thenReturnEmptyList() {
        // Given
        Long choferId = 1L;
        when(choferesViajesRepository.findViajesByChoferId(choferId)).thenReturn(new ArrayList<>());

        // When
        List<Viajes> actualViajes = viajesService.findViajesByChoferId(choferId);

        // Then
        assertEquals(0, actualViajes.size());
    }

    @Test
    public void testFindViajesByClienteId_whenClientesViajesRepositoryThrowsException_thenThrowException() {
        // Given
        Long clienteId = 1L;
        when(clientesViajesRepository.findViajesClienteId(clienteId)).thenThrow(new RuntimeException("ClientesViajesRepository error"));

        // Then
        assertThrows(RuntimeException.class, () -> viajesService.findViajesByClienteId(clienteId));
    }

    @Test
    public void testFindViajesByChoferId_whenChoferesViajesRepositoryThrowsException_thenThrowException() {
        // Given
        Long choferId = 1L;
        when(choferesViajesRepository.findViajesByChoferId(choferId)).thenThrow(new RuntimeException("ChoferesViajesRepository error"));

        // Then
        assertThrows(RuntimeException.class, () -> viajesService.findViajesByChoferId(choferId));
    }
}
