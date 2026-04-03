package com.nexuscar.system.nexuscarsystem.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record LoginDTO(

        @NotBlank
        @NotNull
        String email,

        @NotBlank
        @NotNull
        String password
) {
}
