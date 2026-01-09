package com.nexuscar.system.nexuscarsystem.domain.DTO.fipe;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ConsultarMarcaRequestDTO(

        @NotBlank
        @NotNull
        String marca
) {
}
