package com.nexuscar.system.nexuscarsystem.domain.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ConsultarModelosPorMarcaDTO(
        @JsonProperty("code")
        String codigo,

        @JsonProperty("name")
        String nome
) {}


