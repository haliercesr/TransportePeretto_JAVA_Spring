package com.peretto.sgPeretto.utilitys.mapper;


import com.peretto.sgPeretto.dto.GastosViajesRequest;
import com.peretto.sgPeretto.dto.GastosViajesResponse;
import com.peretto.sgPeretto.entity.GastosViajes;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GastosViajesMapper {

    GastosViajesMapper INSTANCE = Mappers.getMapper(GastosViajesMapper.class);

    GastosViajes toEntity(GastosViajesRequest gastosViajeRequest);
    GastosViajesResponse toResponse(GastosViajes gastosViaje);


}
