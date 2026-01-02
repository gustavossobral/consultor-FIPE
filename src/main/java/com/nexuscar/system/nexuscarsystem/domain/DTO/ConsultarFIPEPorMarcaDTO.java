package com.nexuscar.system.nexuscarsystem.domain.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ConsultarFIPEPorMarcaDTO(
        @JsonProperty("name")
        String Marca,

        @JsonProperty("code")
        String Codigo
) {}


