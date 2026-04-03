package com.nexuscar.system.nexuscarsystem.dto.fipe;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DetalharModeloRequestDTO(

        @NotNull
        @NotBlank
        String marcaId,

        @NotNull
        @NotBlank
        String modeloId,

        @NotNull
        @NotBlank
        String anoId
) {
}
