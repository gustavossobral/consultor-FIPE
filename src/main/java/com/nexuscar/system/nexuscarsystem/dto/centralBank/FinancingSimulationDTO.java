package com.nexuscar.system.nexuscarsystem.dto.centralBank;

import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record FinancingSimulationDTO(

        @NotNull
        BigDecimal fipeValue,

        @NotNull
        BigDecimal registrationFee,

        @NotNull
        BigDecimal downPayment,

        @NotNull
        Integer months
) {
}
