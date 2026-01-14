package com.nexuscar.system.nexuscarsystem.domain.service;

import com.nexuscar.system.nexuscarsystem.domain.DTO.carro.filtrar.CarrosFiltradosDTO;
import com.nexuscar.system.nexuscarsystem.domain.DTO.carro.filtrar.FiltrarCarrosPorPrecoDTO;
import com.nexuscar.system.nexuscarsystem.domain.DTO.carro.filtrar.FiltrarCarrosPorQuilometragemDTO;
import com.nexuscar.system.nexuscarsystem.domain.entity.carro.CarroEntity;
import com.nexuscar.system.nexuscarsystem.domain.entity.carro.CarroRepository;
import com.nexuscar.system.nexuscarsystem.domain.entity.carro.Status;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class InventarioService {

    @Autowired
    private CarroRepository repository;

    public List<CarrosFiltradosDTO> filtrarCarrosPorPreco(FiltrarCarrosPorPrecoDTO dto){

        var carrosFiltrados = repository.findByPrecoBetweenAndStatus(dto.min(), dto.max(), Status.DISPONIVEL);
        var response = carrosFiltrados.stream().map( c -> new CarrosFiltradosDTO(
                c.getId(), c.getModelo(), c.getMarca(), c.getPreco(), c.getAnoFabricacao(), c.getQuilometragem()
                )).toList();

        return response;
    }

    public List<CarrosFiltradosDTO> filtrarCarrosPorQuilometragem(FiltrarCarrosPorQuilometragemDTO dto){

        var carrosFiltrados = repository.findByQuilometragemBetweenAndStatus(dto.min(), dto.max(), Status.DISPONIVEL);
        var response = carrosFiltrados.stream().map( c -> new CarrosFiltradosDTO(
                c.getId(), c.getModelo(), c.getMarca(), c.getPreco(), c.getAnoFabricacao(), c.getQuilometragem()
        )).toList();

        return response;
    }

    public List<CarrosFiltradosDTO> filtrarCarrosDisponiveis(){

        var carrosDisponiveis = repository.findByStatus(Status.DISPONIVEL);
        var response = carrosDisponiveis.stream().map(c -> new CarrosFiltradosDTO(
                c.getId(), c.getModelo(), c.getMarca(), c.getPreco(), c.getAnoFabricacao(), c.getQuilometragem()
        )).toList();

        return response;
    }

    public void reservarCarro(CarroEntity carro){

        if(carro.getStatus() == Status.DISPONIVEL) {
            var dataAtual = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
            carro.setInicioReserva(dataAtual);
            carro.setStatus(Status.RESERVADO);
            repository.save(carro);
        } else {
            throw new RuntimeException("Erro na reserva do veículo.");
        }
    }

    public void venderCarro(CarroEntity carro){

        if(carro.getStatus() == Status.RESERVADO) {
            carro.setInicioReserva(null);
            carro.setStatus(Status.VENDIDO);
            repository.save(carro);
        } else {
            throw new RuntimeException("Erro na venda do veículo");
        }
    }

    @Scheduled(fixedRate = 900000)
    @Transactional
    public void rotinaDeLimpezaDeReservas(){
        var agora = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));
        List<CarroEntity> carrosReservados = repository.findByStatus(Status.RESERVADO);

        for (CarroEntity carro : carrosReservados) {
            if (carro.getInicioReserva() != null && carro.getInicioReserva().plusHours(24).isBefore(agora)){
                carro.setStatus(Status.DISPONIVEL);
                carro.setInicioReserva(null);
                repository.save(carro);
            }
        }
    }

}
