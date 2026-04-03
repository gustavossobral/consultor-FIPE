package com.nexuscar.system.nexuscarsystem.controller;

import com.nexuscar.system.nexuscarsystem.domain.DTO.centralBank.BacenRateDTO;
import com.nexuscar.system.nexuscarsystem.domain.DTO.centralBank.FinancingSimulationDTO;
import com.nexuscar.system.nexuscarsystem.domain.DTO.centralBank.FinancingSimulationResponseDTO;
import com.nexuscar.system.nexuscarsystem.domain.service.SimulatorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/simulador")
public class SimuladorController {

    @Autowired
    SimulatorService service;

    @GetMapping("/bacen")
    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    public ResponseEntity<BacenRateDTO> consultCurrentBacen(){

        BacenRateDTO bacen = service.getBacenValue();

        return ResponseEntity.ok(bacen);
    }

    @PostMapping("/valor-parcela")
    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    public ResponseEntity<FinancingSimulationResponseDTO> simularFinanciamento(@RequestBody FinancingSimulationDTO dto){

        FinancingSimulationResponseDTO simulation = service.simulate(dto);

        return ResponseEntity.ok(simulation);
    }

}
