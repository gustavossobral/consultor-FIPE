package com.nexuscar.system.nexuscarsystem.domain.DTO.cliente;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record EnderecoDTO(
        @NotBlank
        @NotNull
        String logradouro,

        @NotNull
        short numero,

        String complemento,

        @NotBlank
        @NotNull
        String bairro,

        @NotBlank
        @NotNull
        String cidade,

        @NotBlank
        @NotNull
        String uf,

        @NotNull
        int cep
) {}