package com.nexuscar.system.nexuscarsystem.domain.client;

import com.nexuscar.system.nexuscarsystem.domain.DTO.ConsultarFIPEPorMarcaDTO;
import com.nexuscar.system.nexuscarsystem.domain.DTO.ConsultarModelosPorMarcaDTO;
import com.nexuscar.system.nexuscarsystem.domain.DTO.DetalharModeloDTO;
import com.nexuscar.system.nexuscarsystem.domain.DTO.ObterAnosPorModeloDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "FipeOnline", url = "https://fipe.parallelum.com.br/api/v2")
public interface BrasilApiClient {

    @GetMapping("/cars/brands")
    List<ConsultarFIPEPorMarcaDTO> consultarCodigoPorMarca();

    @GetMapping("/cars/brands/{brandId}/models")
    List<ConsultarModelosPorMarcaDTO> consultarModelosPorMarca(@PathVariable("brandId") String brandId);

    @GetMapping("/cars/brands/{brandId}/models/{modelsId}/years")
    List<ObterAnosPorModeloDTO> obterAnosPorModelo(@PathVariable("brandId") String brandId, @PathVariable("modelsId") String modelsId);

    @GetMapping("/cars/brands/{brandId}/models/{modelsId}/years/{yearsId}")
    DetalharModeloDTO detalharModelo(@PathVariable("brandId") String brandId, @PathVariable("modelsId") String modelsId, @PathVariable("yearsId") String yearsId);

}