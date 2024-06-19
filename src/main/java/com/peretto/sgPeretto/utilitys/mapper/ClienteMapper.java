package com.peretto.sgPeretto.utilitys.mapper;


import com.peretto.sgPeretto.dto.ClienteRequest;
import com.peretto.sgPeretto.dto.ClienteResponse;
import com.peretto.sgPeretto.entity.Clientes;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper
public interface ClienteMapper {


    ClienteMapper INSTANCE = Mappers.getMapper(ClienteMapper.class);


    ClienteResponse toDto(Clientes clientes);


    Clientes toEntity(ClienteRequest clienteRequest);



}
