package com.nexuscar.system.nexuscarsystem.dto.fipe;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ConsultBrandsDTO(
        @JsonProperty("name")
        String Marca,

        @JsonProperty("code")
        String Codigo
) {}


