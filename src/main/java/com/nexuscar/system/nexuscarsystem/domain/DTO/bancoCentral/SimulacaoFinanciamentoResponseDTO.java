package com.nexuscar.system.nexuscarsystem.domain.DTO.bancoCentral;

import java.math.BigDecimal;

public record SimulacaoFinanciamentoResponseDTO(
        BigDecimal valorParcela,
        BigDecimal valorTotalFinanciado,
        BigDecimal valorIof,
        BigDecimal valorTotalPago,
        BigDecimal totalTaxasAdicionais,
        BigDecimal custoEfetivoTotal,
        Double taxaJurosAplicada
) {
}
