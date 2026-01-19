package com.nexuscar.system.nexuscarsystem.domain.DTO.usuario;

import com.nexuscar.system.nexuscarsystem.domain.entity.usuario.UserRole;

public record CadastrarUsuarioResponseDTO(

        Long id,
        String nome,
        String email,
        long telefone,
        UserRole role

) {
}
