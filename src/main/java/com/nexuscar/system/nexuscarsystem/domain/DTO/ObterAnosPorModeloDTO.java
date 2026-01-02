package com.nexuscar.system.nexuscarsystem.domain.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ObterAnosPorModeloDTO(
        @JsonProperty("code")
        String codigo,

        @JsonProperty("name")
        String nome
) {
}
