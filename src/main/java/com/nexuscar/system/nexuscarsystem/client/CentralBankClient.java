package com.nexuscar.system.nexuscarsystem.client;

import com.nexuscar.system.nexuscarsystem.dto.centralBank.BacenRateDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@FeignClient(name = "BancoCentral", url = "https://api.bcb.gov.br")
public interface CentralBankClient {

    @GetMapping("/dados/serie/bcdata.sgs.25471/dados/ultimos/1?formato=json")
    List<BacenRateDTO> getBacenValue();

}
