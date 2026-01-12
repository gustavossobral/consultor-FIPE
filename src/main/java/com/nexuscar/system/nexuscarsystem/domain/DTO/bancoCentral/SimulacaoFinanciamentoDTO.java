package com.nexuscar.system.nexuscarsystem.domain.DTO.bancoCentral;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record SimulacaoFinanciamentoDTO(

        @NotNull
        BigDecimal valorFIPE,

        @NotNull
        BigDecimal valorRegistro,

        @NotNull
        BigDecimal valorEntrada,

        @NotNull
        Integer qtdMeses
) {
}
