package com.nexuscar.system.nexuscarsystem.dto.user;

import com.nexuscar.system.nexuscarsystem.model.enums.UserRole;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record RegisterUserDTO(

        @NotNull
        @NotBlank
        String name,

        @NotNull
        @NotBlank
        String email,

        @NotNull
        @NotBlank
        String password,

        @NotNull
        String phone,

        @NotNull
        String cpf,

        @Valid
        AddressDTO address,

        @NotNull
        UserRole role
) {}
