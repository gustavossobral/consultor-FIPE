package com.nexuscar.system.nexuscarsystem.domain.DTO;

import com.fasterxml.jackson.annotation.JsonProperty;

public record DetalharModeloDTO(

        @JsonProperty("vehicleType")
        String tipoDoVeiculo,

        @JsonProperty("price")
        String preco,

        @JsonProperty("brand")
        String marca,

        @JsonProperty("model")
        String modelo,

        @JsonProperty("modelYear")
        String anoDoModelo,

        @JsonProperty("fuel")
        String combustivel,

        @JsonProperty("codeFipe")
        String codigoFipe,

        @JsonProperty("referenceMonth")
        String mesDeReferencia,

        @JsonProperty("fuelAcronym")
        String siglaCombustivel
) {}
