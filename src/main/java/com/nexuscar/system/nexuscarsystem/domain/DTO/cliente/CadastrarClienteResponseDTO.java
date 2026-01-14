package com.nexuscar.system.nexuscarsystem.domain.DTO.cliente;

public record CadastrarClienteResponseDTO(

        Long id,
        String nome,
        String email,
        long telefone

) {
}
