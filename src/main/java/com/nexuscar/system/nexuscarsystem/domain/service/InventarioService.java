package com.nexuscar.system.nexuscarsystem.domain.service;

import com.nexuscar.system.nexuscarsystem.domain.DTO.carro.filtrar.CarrosFiltradosDTO;
import com.nexuscar.system.nexuscarsystem.domain.DTO.carro.filtrar.FiltrarCarrosPorPrecoDTO;
import com.nexuscar.system.nexuscarsystem.domain.DTO.carro.filtrar.FiltrarCarrosPorQuilometragemDTO;
import com.nexuscar.system.nexuscarsystem.domain.entity.carro.CarroRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventarioService {

    @Autowired
    private CarroRepository repository;

    public List<CarrosFiltradosDTO> filtrarCarrosPorPreco(FiltrarCarrosPorPrecoDTO dto){

        var carrosFiltrados = repository.findByPrecoBetween(dto.min(), dto.max());
        var response = carrosFiltrados.stream().map( c -> new CarrosFiltradosDTO(
                c.getId(), c.getModelo(), c.getMarca(), c.getPreco(), c.getAnoFabricacao(), c.getQuilometragem()
                )).toList();

        return response;
    }

    public List<CarrosFiltradosDTO> filtrarCarrosPorQuilometragem(FiltrarCarrosPorQuilometragemDTO dto){

        var carrosFiltrados = repository.findByQuilometragemBetween(dto.min(), dto.max());
        var response = carrosFiltrados.stream().map( c -> new CarrosFiltradosDTO(
                c.getId(), c.getModelo(), c.getMarca(), c.getPreco(), c.getAnoFabricacao(), c.getQuilometragem()
        )).toList();

        return response;
    }

}
