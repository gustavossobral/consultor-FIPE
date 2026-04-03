package com.nexuscar.system.nexuscarsystem.domain.DTO.fipe;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ConsultBrandsDTO(
        @JsonProperty("name")
        String Marca,

        @JsonProperty("code")
        String Codigo
) {}


