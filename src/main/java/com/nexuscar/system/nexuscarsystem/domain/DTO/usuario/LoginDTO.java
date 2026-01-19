package com.nexuscar.system.nexuscarsystem.domain.DTO.usuario;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginDTO(

        @NotBlank
        @NotNull
        String email,

        @NotBlank
        @NotNull
        String senha
) {
}
