package com.nexuscar.system.nexuscarsystem.dto.user;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record AddressDTO(
        @NotBlank
        @NotNull
        String street,

        @NotNull
        Short number,

        String complement,

        @NotBlank
        @NotNull
        String neighborhood,

        @NotBlank
        @NotNull
        String city,

        @NotBlank
        @NotNull
        String uf,

        @NotNull
        String cep
) {}