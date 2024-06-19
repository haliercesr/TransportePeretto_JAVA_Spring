package com.peretto.sgPeretto.utilitys.mapper;


import com.peretto.sgPeretto.dto.*;
import com.peretto.sgPeretto.entity.Choferes;
import com.peretto.sgPeretto.entity.Clientes;
import com.peretto.sgPeretto.entity.GastosViajes;
import com.peretto.sgPeretto.entity.Viajes;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface ViajesMapper {

ViajesMapper INSTANCE = Mappers.getMapper(ViajesMapper.class);
    Viajes toEntity(ViajesResponse viaje);
    Viajes toEntity(ViajesRequest viajesRequest);
    Viajes toEntity(ViajesUpdateRequest viajesUpdateRequest);
    Clientes toEntity(ClienteRequest clienteRequest);
    Choferes toEntity(ChoferRequest choferRequest);
    ViajesResponse toResponse(Viajes viajes);

    ClienteResponse toResponse(Clientes cliente);
    ChoferResponse toResponse(Choferes chofer);
    GastosViajes toEntity(GastosViajesRequest gastosViajeRequest);
    GastosViajesResponse toResponse(GastosViajes gastosViaje);

}
