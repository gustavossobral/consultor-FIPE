package com.nexuscar.system.nexuscarsystem.domain.service;

import com.nexuscar.system.nexuscarsystem.domain.DTO.fipe.*;
import com.nexuscar.system.nexuscarsystem.domain.client.BrasilApiClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FipeService {

    @Autowired
    private BrasilApiClient consultarFipe;

    public ConsultarFIPEPorMarcaDTO buscarPorMarca(ConsultarMarcaRequestDTO marca){
        List<ConsultarFIPEPorMarcaDTO> todasAsMarcas = consultarFipe.consultarCodigoPorMarca();

        return todasAsMarcas.stream()
                .filter(m -> m.Marca().equalsIgnoreCase(marca.marca()))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Marca não encontrada"));
    }

    public List<ConsultarModelosPorMarcaDTO> buscarModelosPorMarca(ConsultarModelosRequestDTO request){
        var response = consultarFipe.consultarModelosPorMarca(request.marca());

        return response;
    }

    public List<ObterAnosPorModeloDTO> obterAnosPorModelo(ObterAnosPorModeloRequestDTO request){
        var response = consultarFipe.obterAnosPorModelo(request.marcaId(),request.modeloId());

        return response;
    }

    public DetalharModeloDTO detalharModelo(DetalharModeloRequestDTO request){
        var response = consultarFipe.detalharModelo(request.marcaId(), request.modeloId(), request.anoId());

        return response;
    }
}

