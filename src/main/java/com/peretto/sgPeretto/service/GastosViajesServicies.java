package com.peretto.sgPeretto.service;


import com.peretto.sgPeretto.dto.CloseViajeResponse;
import com.peretto.sgPeretto.dto.GastosViajesRequest;
import com.peretto.sgPeretto.dto.GastosViajesResponse;
import com.peretto.sgPeretto.entity.GastosViajes;
import com.peretto.sgPeretto.entity.Viajes;
import com.peretto.sgPeretto.exception.ResourceNotFoundException;
import com.peretto.sgPeretto.repository.GastosViajesRepository;
import com.peretto.sgPeretto.repository.ViajesRepository;
import com.peretto.sgPeretto.utilitys.mapper.GastosViajesMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class GastosViajesServicies {

    @Autowired
    private GastosViajesRepository gastosViajesRepository;

    @Autowired
    private ViajesRepository viajesRepository;

    public GastosViajesResponse createGastosViajes(Long viajeId, GastosViajesRequest request){
        Viajes viaje = viajesRepository.findById(viajeId)
                .orElseThrow(() ->  new IllegalArgumentException("viaje No Encontrado"));
        GastosViajes gastosViajes = GastosViajesMapper.INSTANCE.toEntity(request);
        gastosViajes.setViaje(viaje);
        GastosViajes saveGastosviajes = gastosViajesRepository.save(gastosViajes);
        return GastosViajesMapper.INSTANCE.toResponse(saveGastosviajes);

    }

    public List<GastosViajesResponse> getAllGastosViaje() {
        List<GastosViajes> gastosViajes = gastosViajesRepository.findAll();
        return gastosViajes.stream()
                .map(GastosViajesMapper.INSTANCE::toResponse)
                .collect(Collectors.toList());
    }


//    public GastosViajesResponse getGastoViajeId(Long id){
//
//        GastosViajes gastosViajes = gastosViajesRepository.findById(id)
//                .orElseThrow(()->  new IllegalArgumentException("Gastos No encontrado"));
//                return GastosViajesMapper.INSTANCE.toResponse(gastosViajes);
//    }

    public GastosViajesResponse updateGastosViajes(Long id, GastosViajesRequest request){
       GastosViajes existingGastosViaje = gastosViajesRepository.findById(id)
               .orElseThrow(()->  new IllegalArgumentException("Gastos No encontrado"));

        existingGastosViaje.setGastosPeajes(request.getGastosPeajes());
        existingGastosViaje.setGastosCombustible(request.getGastosCombustible());
        existingGastosViaje.setGastosExtras(request.getGastosExtras());
        existingGastosViaje.setDetalleGastosExtra(request.getDetalleGastosExtra());

GastosViajes updateGastos = gastosViajesRepository.save(existingGastosViaje);

return GastosViajesMapper.INSTANCE.toResponse(updateGastos);


    }



    public List<GastosViajesResponse> getGastosByViajeId(Long viajeId) {
        Viajes viaje = viajesRepository.findById(viajeId)
                .orElseThrow(() -> new IllegalArgumentException("Gasto no encontrado"));

        Optional<GastosViajes> gastosViajesList = gastosViajesRepository.findByViaje(viaje);
        return gastosViajesList.stream()
                .map(GastosViajesMapper.INSTANCE::toResponse)
                .collect(Collectors.toList());
    }



    public CloseViajeResponse closeViaje(Long viajeId){
        Viajes viaje = viajesRepository.findById(viajeId)
                .orElseThrow(()-> new ResourceNotFoundException("Viaje no Encontrado"));
        Optional<GastosViajes> gastosViajesList= gastosViajesRepository.findById(viajeId);
        double totalGastos =  gastosViajesList.stream()
                .mapToDouble(gasto -> gasto.getGastosCombustible() + gasto.getGastosPeajes() + gasto.getGastosExtras())
                .sum();

        double diferencia = viaje.getMontoOtorgado() - totalGastos;
        String mensaje;
        if(diferencia >0){
                mensaje= "Sobrante de : " + diferencia + " Gasto Total :  " +  totalGastos;

        }else if(diferencia < 0){

            mensaje = "Deficit de " + Math.abs(diferencia) + " Gasto Total :  " +  totalGastos;

        }else{
            mensaje = "El monto otorgado es igual a el total d gastos";
        }
        viaje.setViajeRealizado(true);
        viajesRepository.save(viaje);

        return new CloseViajeResponse(diferencia, mensaje);


    }


    public boolean deleteGastosViajes(Long id){
       if( gastosViajesRepository.existsById(id)){
           gastosViajesRepository.deleteById(id);
       return true;
       }else{
            throw new ResourceNotFoundException("Gasto no encontrada");
    }
    }

}
