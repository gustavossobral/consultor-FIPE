package com.nexuscar.system.nexuscarsystem.service;

import com.nexuscar.system.nexuscarsystem.dto.centralBank.BacenRateDTO;
import com.nexuscar.system.nexuscarsystem.dto.centralBank.FinancingSimulationDTO;
import com.nexuscar.system.nexuscarsystem.dto.centralBank.FinancingSimulationResponseDTO;
import com.nexuscar.system.nexuscarsystem.client.CentralBankClient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class SimulatorService {

    @Autowired
    CentralBankClient consultBacen;

    public BacenRateDTO getBacenValue(){

        BacenRateDTO value = consultBacen.getBacenValue().get(0);

        return value;
    }

    public BigDecimal calculeIOFValue(BigDecimal netFinancedAmount, int months) {

        BigDecimal estimatedRate;

        if (months <= 12) {
            estimatedRate = new BigDecimal("0.015");
        } else if (months <= 36) {
            estimatedRate = new BigDecimal("0.022");
        } else {
            estimatedRate = new BigDecimal("0.0338");
        }

        return netFinancedAmount.multiply(estimatedRate);
    }

    public FinancingSimulationResponseDTO simulate(@Valid @NotNull FinancingSimulationDTO dto) {
        var averageRegistrationFee = new BigDecimal("900.00");
        var fees = dto.registrationFee().add(averageRegistrationFee).setScale(2, RoundingMode.HALF_UP);

        var netFinancedAmount = dto.fipeValue().subtract(dto.downPayment()).add(fees).setScale(2, RoundingMode.HALF_UP);
        var iof = calculeIOFValue(netFinancedAmount, dto.months()).setScale(2, RoundingMode.HALF_UP);

        var grossFinancedAmount = netFinancedAmount.add(iof).setScale(2, RoundingMode.HALF_UP);

        double monthlyInterestRate = Double.parseDouble(getBacenValue().value())/100;
        var formattedInterestRate = BigDecimal.valueOf(monthlyInterestRate * 100).setScale(2, RoundingMode.HALF_UP);
        int n = dto.months();

        double factor = Math.pow(1 + monthlyInterestRate, n);
        double coefficient = (monthlyInterestRate * factor) / (factor - 1);

        var installmentAmount = grossFinancedAmount.multiply(BigDecimal.valueOf(coefficient)).setScale(2, RoundingMode.HALF_UP);

        var totalAmountPaid = installmentAmount.multiply(BigDecimal.valueOf(n)).setScale(2, RoundingMode.HALF_UP);

        var originalDebt = dto.fipeValue().subtract(dto.downPayment()).setScale(2, RoundingMode.HALF_UP);
        var totalEffectiveCost = totalAmountPaid.subtract(originalDebt).setScale(2, RoundingMode.HALF_UP);

        return new FinancingSimulationResponseDTO(
                installmentAmount,
                grossFinancedAmount,
                iof,
                totalAmountPaid,
                fees,
                totalEffectiveCost,
                formattedInterestRate.doubleValue()
        );
    }

}
