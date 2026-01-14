package com.nexuscar.system.nexuscarsystem.domain.DTO.cliente;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record CadastrarClienteDTO(

        @NotNull
        @NotBlank
        String nome,

        @NotNull
        @NotBlank
        String email,

        @NotNull
        long telefone,

        @NotNull
        long cpf,

        @Valid
        EnderecoDTO endereco
) {}
