package com.nexuscar.system.nexuscarsystem.domain.DTO.carro.filtrar;

import jakarta.validation.constraints.NotNull;

public record FiltrarCarrosPorQuilometragemDTO(

        @NotNull
        int max,

        @NotNull
        int min

) {
}
