package com.nexuscar.system.nexuscarsystem.dto.fipe;

import com.fasterxml.jackson.annotation.JsonProperty;

public record YearsPerModelDTO(
        @JsonProperty("code")
        String codigo,

        @JsonProperty("name")
        String nome
) {
}
