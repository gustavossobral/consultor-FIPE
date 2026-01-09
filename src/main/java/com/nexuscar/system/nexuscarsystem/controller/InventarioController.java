package com.nexuscar.system.nexuscarsystem.controller;

import com.nexuscar.system.nexuscarsystem.domain.DTO.carro.cadastro.CadastrarCarroDTO;
import com.nexuscar.system.nexuscarsystem.domain.DTO.carro.cadastro.CadastroCarroResponseDTO;
import com.nexuscar.system.nexuscarsystem.domain.DTO.carro.filtrar.CarrosFiltradosDTO;
import com.nexuscar.system.nexuscarsystem.domain.DTO.carro.filtrar.FiltrarCarrosPorPrecoDTO;
import com.nexuscar.system.nexuscarsystem.domain.DTO.carro.filtrar.FiltrarCarrosPorQuilometragemDTO;
import com.nexuscar.system.nexuscarsystem.domain.entity.carro.CarroEntity;
import com.nexuscar.system.nexuscarsystem.domain.entity.carro.CarroRepository;
import com.nexuscar.system.nexuscarsystem.domain.service.InventarioService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    private CarroRepository repository;

    @Autowired
    private InventarioService service;

    @PostMapping("/cadastrar")
    public ResponseEntity<CadastroCarroResponseDTO> cadastrarCarro(@RequestBody @Valid CadastrarCarroDTO dto){

        var carro = new CarroEntity(dto);
        repository.save(carro);

        var response = new CadastroCarroResponseDTO(
                carro.getModelo(), carro.getMarca(), carro.getPreco(), carro.getQuilometragem()
        );

        return ResponseEntity.ok(response);
    }

    @PostMapping("/filtrar/preco")
    public ResponseEntity<List<CarrosFiltradosDTO>> filtarPorPreco(@RequestBody @Valid FiltrarCarrosPorPrecoDTO dto){

        var carros = service.filtrarCarrosPorPreco(dto);

        return ResponseEntity.ok(carros);
    }

    @PostMapping("/filtrar/quilometragem")
    public ResponseEntity<List<CarrosFiltradosDTO>> filtrarPorQuilometragem(@RequestBody @Valid FiltrarCarrosPorQuilometragemDTO dto){

      var carros = service.filtrarCarrosPorQuilometragem(dto);

      return ResponseEntity.ok(carros);
    }

}
