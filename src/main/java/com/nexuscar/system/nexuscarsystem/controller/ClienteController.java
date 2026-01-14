package com.nexuscar.system.nexuscarsystem.controller;

import com.nexuscar.system.nexuscarsystem.domain.DTO.cliente.CadastrarClienteDTO;
import com.nexuscar.system.nexuscarsystem.domain.DTO.cliente.CadastrarClienteResponseDTO;
import com.nexuscar.system.nexuscarsystem.domain.entity.cliente.ClienteEntity;
import com.nexuscar.system.nexuscarsystem.domain.entity.cliente.ClienteRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/cliente")
public class ClienteController {

    @Autowired
    private ClienteRepository repository;

    @PostMapping("/cadastrar")
    @Transactional
    public ResponseEntity<CadastrarClienteResponseDTO> cadastrarCliente(@RequestBody @Valid CadastrarClienteDTO dto, UriComponentsBuilder uriBuilder){

        var cliente = new ClienteEntity(dto);
        repository.save(cliente);

        var uri = uriBuilder.path("/cliente/cadastro/{id}").buildAndExpand(cliente.getId()).toUri();
        var response = new CadastrarClienteResponseDTO(cliente.getId(),cliente.getNome(), cliente.getEmail(), cliente.getTelefone());

       return ResponseEntity.created(uri).body(response);
    }


}
