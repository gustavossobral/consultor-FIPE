package com.nexuscar.system.nexuscarsystem.domain.client;

import com.nexuscar.system.nexuscarsystem.domain.DTO.bancoCentral.ObterBacenDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "BancoCentral", url = "https://api.bcb.gov.br")
public interface BancoCentralClient {

    @GetMapping("/dados/serie/bcdata.sgs.25471/dados/ultimos/1?formato=json")
    List<ObterBacenDTO> obterBacen();

}
