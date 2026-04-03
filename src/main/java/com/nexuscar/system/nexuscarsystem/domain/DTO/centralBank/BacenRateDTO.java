package com.nexuscar.system.nexuscarsystem.domain.DTO.centralBank;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BacenRateDTO(

        @JsonProperty("data")
        String data,

        @JsonProperty("valor")
        String value

) {
}
