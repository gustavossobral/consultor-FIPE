package com.nexuscar.system.nexuscarsystem.domain.DTO.fipe;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ModelsPerBrandDTO(
        @JsonProperty("code")
        String codigo,

        @JsonProperty("name")
        String nome
) {}


