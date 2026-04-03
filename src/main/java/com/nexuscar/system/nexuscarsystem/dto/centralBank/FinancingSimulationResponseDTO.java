package com.nexuscar.system.nexuscarsystem.dto.centralBank;

import java.math.BigDecimal;

public record FinancingSimulationResponseDTO(

        BigDecimal installmentAmount,
        BigDecimal totalFinancedAmount,
        BigDecimal iofAmount,
        BigDecimal totalAmountPaid,
        BigDecimal totalAdditionalFees,
        BigDecimal totalEffectiveCost,
        Double appliedInterestRate

) {
}
