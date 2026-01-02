package com.nexuscar.system.nexuscarsystem.domain.DTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record ConsultarModelosRequestDTO(
        @NotNull
        @NotBlank
        String marca
) {
}
