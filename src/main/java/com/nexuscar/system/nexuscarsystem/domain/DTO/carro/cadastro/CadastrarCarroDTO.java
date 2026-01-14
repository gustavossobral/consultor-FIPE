package com.nexuscar.system.nexuscarsystem.domain.DTO.carro.cadastro;

import com.nexuscar.system.nexuscarsystem.domain.entity.carro.Status;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CadastrarCarroDTO(

        @NotBlank
        @NotNull
        String modelo,

        @NotBlank
        @NotNull
        String marca,

        @NotNull
        Short anoFabricacao,

        @NotNull
        Integer quilometragem,

        @NotNull
        Byte qtdDonos,

        @NotNull
        BigDecimal preco,

        @NotBlank
        @NotNull
        String cor,

        @NotBlank
        @NotNull
        String combustivel,

        String observacoes
) {
}
