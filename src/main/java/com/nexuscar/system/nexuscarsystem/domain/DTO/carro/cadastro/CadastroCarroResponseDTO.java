package com.nexuscar.system.nexuscarsystem.domain.DTO.carro.cadastro;

import java.math.BigDecimal;

public record CadastroCarroResponseDTO(

        String modelo,
        String marca,
        BigDecimal preco,
        int quilometragem

) {
}
