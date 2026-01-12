package com.nexuscar.system.nexuscarsystem.controller;

import com.nexuscar.system.nexuscarsystem.domain.DTO.bancoCentral.ObterBacenDTO;
import com.nexuscar.system.nexuscarsystem.domain.DTO.bancoCentral.SimulacaoFinanciamentoDTO;
import com.nexuscar.system.nexuscarsystem.domain.DTO.bancoCentral.SimulacaoFinanciamentoResponseDTO;
import com.nexuscar.system.nexuscarsystem.domain.service.SimuladorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/simulador")
public class SimuladorController {

    @Autowired
    SimuladorService service;

    @GetMapping("/bacen")
    public ResponseEntity<ObterBacenDTO> consultarBacenAtual(){

        var bacen = service.obterValorBacen();

        return ResponseEntity.ok(bacen);
    }

    @PostMapping("/valor-parcela")
    public ResponseEntity<SimulacaoFinanciamentoResponseDTO> simularFinanciamento(@RequestBody SimulacaoFinanciamentoDTO dto){

        var response = service.simularFinanciamento(dto);

        return ResponseEntity.ok(response);
    }

}
