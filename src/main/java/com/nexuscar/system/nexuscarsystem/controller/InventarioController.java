package com.nexuscar.system.nexuscarsystem.controller;

import com.nexuscar.system.nexuscarsystem.domain.DTO.carro.cadastro.CadastrarCarroDTO;
import com.nexuscar.system.nexuscarsystem.domain.DTO.carro.cadastro.CadastroCarroResponseDTO;
import com.nexuscar.system.nexuscarsystem.domain.DTO.carro.filtrar.CarrosFiltradosDTO;
import com.nexuscar.system.nexuscarsystem.domain.DTO.carro.filtrar.FiltrarCarrosPorPrecoDTO;
import com.nexuscar.system.nexuscarsystem.domain.DTO.carro.filtrar.FiltrarCarrosPorQuilometragemDTO;
import com.nexuscar.system.nexuscarsystem.domain.DTO.cliente.CadastrarClienteResponseDTO;
import com.nexuscar.system.nexuscarsystem.domain.entity.carro.CarroEntity;
import com.nexuscar.system.nexuscarsystem.domain.entity.carro.CarroRepository;
import com.nexuscar.system.nexuscarsystem.domain.entity.carro.Status;
import com.nexuscar.system.nexuscarsystem.domain.service.InventarioService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/inventario")
public class InventarioController {

    @Autowired
    private CarroRepository repository;

    @Autowired
    private InventarioService service;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<CadastroCarroResponseDTO> cadastrarCarro(@RequestBody @Valid CadastrarCarroDTO dto, UriComponentsBuilder uriBuilder){

        var carro = new CarroEntity(dto);
        repository.save(carro);

        var uri = uriBuilder.path("/inventario/cadastrar/{id}").buildAndExpand(carro.getId()).toUri();
        var response = new CadastroCarroResponseDTO(carro.getModelo(), carro.getMarca(), carro.getPreco(), carro.getQuilometragem());

        return ResponseEntity.created(uri).body(response);
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

    @GetMapping("/modelos")
    public ResponseEntity<List<CarrosFiltradosDTO>> acessarModelosDisponiveis(){

        var carros = service.filtrarCarrosDisponiveis();

        return ResponseEntity.ok(carros);
    }

    @PostMapping("/reservar/{id}")
    @Transactional
    public ResponseEntity<String> reservarModelo(@PathVariable Long id){

        var carroSelecionado = repository.getReferenceById(id);
        service.reservarCarro(carroSelecionado);
        var response = "Carro " + '"' + carroSelecionado.getModelo() + '"' + " reservado com sucesso! Após 24h, estará disponível novamente.";

        return ResponseEntity.ok(response);
    }

    @PostMapping("/vender/{id}")
    @Transactional
    public ResponseEntity<String> venderModelo(@PathVariable Long id){

        var carroSelecionado = repository.getReferenceById(id);
        service.venderCarro(carroSelecionado);
        var response = "Carro " + '"' + carroSelecionado.getModelo() + '"' + " vendido com sucesso!";

        return ResponseEntity.ok(response);
    }

}
