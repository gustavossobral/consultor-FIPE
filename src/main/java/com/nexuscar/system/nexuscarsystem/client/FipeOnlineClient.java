package com.nexuscar.system.nexuscarsystem.client;

import com.nexuscar.system.nexuscarsystem.dto.fipe.ConsultBrandsDTO;
import com.nexuscar.system.nexuscarsystem.dto.fipe.DetailModelDTO;
import com.nexuscar.system.nexuscarsystem.dto.fipe.ModelsPerBrandDTO;
import com.nexuscar.system.nexuscarsystem.dto.fipe.YearsPerModelDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "FipeOnline", url = "https://fipe.parallelum.com.br/api/v2")
public interface FipeOnlineClient {

    @GetMapping("/cars/brands")
    List<ConsultBrandsDTO> getBrandsCode();

    @GetMapping("/cars/brands/{brandId}/models")
    List<ModelsPerBrandDTO> getModelsPerBrand(@PathVariable("brandId") String brandId);

    @GetMapping("/cars/brands/{brandId}/models/{modelsId}/years")
    List<YearsPerModelDTO> getYearsPerModel(@PathVariable("brandId") String brandId, @PathVariable("modelsId") String modelsId);

    @GetMapping("/cars/brands/{brandId}/models/{modelsId}/years/{yearsId}")
    DetailModelDTO detailModel(@PathVariable("brandId") String brandId, @PathVariable("modelsId") String modelsId, @PathVariable("yearsId") String yearsId);

}