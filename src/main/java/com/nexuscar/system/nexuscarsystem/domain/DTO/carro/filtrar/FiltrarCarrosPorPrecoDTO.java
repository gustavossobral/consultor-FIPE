package com.nexuscar.system.nexuscarsystem.domain.DTO.carro.filtrar;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record FiltrarCarrosPorPrecoDTO(

        @NotNull
        BigDecimal max,

        @NotNull
        BigDecimal min

) {
}
