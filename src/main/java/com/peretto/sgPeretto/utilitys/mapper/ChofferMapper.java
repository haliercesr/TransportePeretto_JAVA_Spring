package com.peretto.sgPeretto.utilitys.mapper;


import com.peretto.sgPeretto.dto.ChoferRequest;
import com.peretto.sgPeretto.dto.ChoferResponse;
import com.peretto.sgPeretto.entity.Choferes;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ChofferMapper {

    ChofferMapper INSTANCE = Mappers.getMapper(ChofferMapper.class);


    ChoferResponse toDto(Choferes choferes);


    Choferes toEntity(ChoferRequest choferRequest);


}
