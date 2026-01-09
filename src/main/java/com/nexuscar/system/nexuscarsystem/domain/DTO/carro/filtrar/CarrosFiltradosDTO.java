package com.nexuscar.system.nexuscarsystem.domain.DTO.carro.filtrar;

import java.math.BigDecimal;

public record CarrosFiltradosDTO(

        Long id,
        String modelo,
        String marca,
        BigDecimal preco,
        short anoFabricacao,
        int quilometragem

) {
}
