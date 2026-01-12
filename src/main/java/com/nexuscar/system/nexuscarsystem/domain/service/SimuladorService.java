package com.nexuscar.system.nexuscarsystem.domain.service;

import com.nexuscar.system.nexuscarsystem.domain.DTO.bancoCentral.ObterBacenDTO;
import com.nexuscar.system.nexuscarsystem.domain.DTO.bancoCentral.SimulacaoFinanciamentoDTO;
import com.nexuscar.system.nexuscarsystem.domain.DTO.bancoCentral.SimulacaoFinanciamentoResponseDTO;
import com.nexuscar.system.nexuscarsystem.domain.client.BancoCentralClient;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.math.RoundingMode;

@Service
public class SimuladorService {

    @Autowired
    BancoCentralClient consultarBacen;

    public ObterBacenDTO obterValorBacen(){

        var valor = consultarBacen.obterBacen().get(0);

        return valor;
    }

    public BigDecimal calcularValorIof(BigDecimal valorBaseFinanciado, int meses) {

        BigDecimal taxaEstimada;

        if (meses <= 12) {
            taxaEstimada = new BigDecimal("0.015");
        } else if (meses <= 36) {
            taxaEstimada = new BigDecimal("0.022");
        } else {
            taxaEstimada = new BigDecimal("0.0338");
        }

        return valorBaseFinanciado.multiply(taxaEstimada);
    }

    public SimulacaoFinanciamentoResponseDTO simularFinanciamento(@Valid @NotNull SimulacaoFinanciamentoDTO dto) {
        var tarifaMediaDeCadastro = new BigDecimal("900.00");
        var tarifas = dto.valorRegistro().add(tarifaMediaDeCadastro).setScale(2, RoundingMode.HALF_UP);

        var valorBaseFinanciado = dto.valorFIPE().subtract(dto.valorEntrada()).add(tarifas).setScale(2, RoundingMode.HALF_UP);
        var iof = calcularValorIof(valorBaseFinanciado, dto.qtdMeses()).setScale(2, RoundingMode.HALF_UP);

        var valorTotalComIof = valorBaseFinanciado.add(iof).setScale(2, RoundingMode.HALF_UP);

        double taxaMensal = Double.parseDouble(obterValorBacen().valor())/100;
        var taxaFormatada = BigDecimal.valueOf(taxaMensal * 100).setScale(2, RoundingMode.HALF_UP);
        int n = dto.qtdMeses();

        double fator = Math.pow(1 + taxaMensal, n);
        double coeficiente = (taxaMensal * fator) / (fator - 1);

        var valorPorParcela = valorTotalComIof.multiply(BigDecimal.valueOf(coeficiente)).setScale(2, RoundingMode.HALF_UP);

        var valorTotalPago = valorPorParcela.multiply(BigDecimal.valueOf(n)).setScale(2, RoundingMode.HALF_UP);

        var saldoDevedorOriginal = dto.valorFIPE().subtract(dto.valorEntrada()).setScale(2, RoundingMode.HALF_UP);
        var custoEfetivoTotal = valorTotalPago.subtract(saldoDevedorOriginal).setScale(2, RoundingMode.HALF_UP);

        return new SimulacaoFinanciamentoResponseDTO(
                valorPorParcela,
                valorTotalComIof,
                iof,
                valorTotalPago,
                tarifas,
                custoEfetivoTotal,
                taxaFormatada.doubleValue()
        );
    }

}
