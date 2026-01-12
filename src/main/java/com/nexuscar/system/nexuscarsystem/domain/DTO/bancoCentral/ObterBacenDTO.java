package com.nexuscar.system.nexuscarsystem.domain.DTO.bancoCentral;

import com.fasterxml.jackson.annotation.JsonProperty;

public record ObterBacenDTO(

        @JsonProperty("data")
        String data,

        @JsonProperty("valor")
        String valor

) {
}
