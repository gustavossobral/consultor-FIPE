package com.nexuscar.system.nexuscarsystem.domain.DTO.carro.filtrar;

import java.math.BigDecimal;

public record CarFilterDTO(

        Long id,
        String modelo,
        String marca,
        Short anoFabricacao,
        Integer quilometragem,
        BigDecimal preco,
        String cor,
        String combustivel


) {
}
