package com.nexuscar.system.nexuscarsystem.domain.entity.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;

public interface UsuarioRepository extends JpaRepository<UsuarioEntity,Long> {

    UserDetails findByEmail(String email);

}
