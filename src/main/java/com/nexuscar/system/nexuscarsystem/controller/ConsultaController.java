package com.nexuscar.system.nexuscarsystem.controller;

import com.nexuscar.system.nexuscarsystem.domain.DTO.fipe.*;
import com.nexuscar.system.nexuscarsystem.domain.service.FipeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/consulta")
public class ConsultaController {

    @Autowired
    private FipeService fipeService;

    @PostMapping("/marca")
    public ResponseEntity<ConsultarFIPEPorMarcaDTO> consultarFIPEPorMarca(@Valid @RequestBody ConsultarMarcaRequestDTO request){

        ConsultarFIPEPorMarcaDTO response = fipeService.buscarPorMarca(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/marca/modelos")
    public ResponseEntity<List<ConsultarModelosPorMarcaDTO>> consultarModelosPorMarca(@Valid @RequestBody ConsultarModelosRequestDTO request){

        List<ConsultarModelosPorMarcaDTO> response = fipeService.buscarModelosPorMarca(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/marca/modelos/anos")
    public ResponseEntity<List<ObterAnosPorModeloDTO>> obterAnosPorModelo(@Valid @RequestBody ObterAnosPorModeloRequestDTO request){

        List<ObterAnosPorModeloDTO> response = fipeService.obterAnosPorModelo(request);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/marca/modelos/detalhar-modelo")
    public ResponseEntity<DetalharModeloDTO> detalharModelo(@Valid @RequestBody DetalharModeloRequestDTO request){

        DetalharModeloDTO response = fipeService.detalharModelo(request);

        return ResponseEntity.ok(response);
    }


}
