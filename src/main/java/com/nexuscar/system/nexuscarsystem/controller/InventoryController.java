package com.nexuscar.system.nexuscarsystem.controller;

import com.nexuscar.system.nexuscarsystem.domain.DTO.carro.CarRegisterDTO;
import com.nexuscar.system.nexuscarsystem.domain.DTO.carro.filtrar.CarFilterDTO;
import com.nexuscar.system.nexuscarsystem.domain.service.InventoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    @Autowired
    private InventoryService service;

    @PostMapping("/register")
    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    public ResponseEntity<Void> register(@RequestBody @Valid CarRegisterDTO dto){

        service.register(dto);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @GetMapping("/filter/price/{min}/{max}")
    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR', 'CLIENTE')")
    public ResponseEntity<Page<CarFilterDTO>> filterByPrice(@PathVariable BigDecimal min, @PathVariable BigDecimal max, @PageableDefault(page = 0, size = 20, sort = "id") Pageable pageable){

        Page<CarFilterDTO> cars = service.filterByPrice(min, max, pageable);

        return ResponseEntity.ok(cars);
    }

    @GetMapping("/filter/mileage/{min}/{max}")
    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR', 'CLIENTE')")
    public ResponseEntity<Page<CarFilterDTO>> filterByMileage(@PathVariable int min, @PathVariable int max, @PageableDefault(page = 0, size = 20, sort = "id") Pageable pageable){

        Page<CarFilterDTO> cars = service.filterByMileage(min,max,pageable);

      return ResponseEntity.ok(cars);
    }

    @GetMapping("/models")
    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR', 'CLIENTE')")
    public ResponseEntity<Page<CarFilterDTO>> getAvailableModels(@PageableDefault(page = 0, size = 20, sort = "id") Pageable pageable){

        Page<CarFilterDTO> cars = service.filterByAvailable(pageable);

        return ResponseEntity.ok(cars);
    }

    @PatchMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    public ResponseEntity<Void> reserve(@PathVariable Long id){

        service.reserve(id);

        return ResponseEntity.noContent().build();
    }

    @PostMapping("/vender/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    public ResponseEntity<Void> sell(@PathVariable Long id){

        service.sell(id);

        return ResponseEntity.noContent().build();
    }

}
