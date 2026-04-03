package com.nexuscar.system.nexuscarsystem.dto.car;

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
