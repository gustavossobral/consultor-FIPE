package com.nexuscar.system.nexuscarsystem.controller;

import com.nexuscar.system.nexuscarsystem.service.FipeService;
import com.nexuscar.system.nexuscarsystem.dto.fipe.ConsultBrandsDTO;
import com.nexuscar.system.nexuscarsystem.dto.fipe.DetailModelDTO;
import com.nexuscar.system.nexuscarsystem.dto.fipe.ModelsPerBrandDTO;
import com.nexuscar.system.nexuscarsystem.dto.fipe.YearsPerModelDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/fipe")
public class InquiryController {

    @Autowired
    private FipeService fipeService;

    @GetMapping("/brand")
    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    public ResponseEntity<Page<ConsultBrandsDTO>> consultBrands(@PageableDefault(page = 0, size = 20, sort = "code") Pageable pageable){

        Page<ConsultBrandsDTO> response = fipeService.searchBrands(pageable);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/brand/{brandId}/model")
    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    public ResponseEntity<Page<ModelsPerBrandDTO>> consultModelsByBrand(@PathVariable String brandId, @PageableDefault(page = 0, size = 20, sort = "code") Pageable pageable){

        Page<ModelsPerBrandDTO> response = fipeService.searchModelsPerBrand(brandId, pageable);

        return ResponseEntity.ok(response);
    }

    @GetMapping("/brand/{brandId}/model/{modelId}/years")
    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    public ResponseEntity<Page<YearsPerModelDTO>> consultModelsByBrandAndYear(@PathVariable String brandId, @PathVariable String modelId, @PageableDefault(page = 0, size = 20, sort = "code") Pageable pageable){

        Page<YearsPerModelDTO> response = fipeService.getYearsByModel(brandId, modelId, pageable);

        return ResponseEntity.ok(response);
    }

    @PostMapping("/brand/{brandId}/model/{modelId}/years/{yearId}")
    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    public ResponseEntity<DetailModelDTO> detailModel(@PathVariable String brandId, @PathVariable String modelId, @PathVariable String yearId){

        DetailModelDTO response = fipeService.detailModel(brandId, modelId, yearId);

        return ResponseEntity.ok(response);
    }


}
