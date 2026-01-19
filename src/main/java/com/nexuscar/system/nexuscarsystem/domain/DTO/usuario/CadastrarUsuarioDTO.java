package com.nexuscar.system.nexuscarsystem.domain.DTO.usuario;

import com.nexuscar.system.nexuscarsystem.domain.entity.usuario.UserRole;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastrarUsuarioDTO(

        @NotNull
        @NotBlank
        String nome,

        @NotNull
        @NotBlank
        String email,

        @NotNull
        @NotBlank
        String senha,

        @NotNull
        long telefone,

        @NotNull
        long cpf,

        @Valid
        EnderecoDTO endereco,

        @NotNull
        UserRole role
) {}
