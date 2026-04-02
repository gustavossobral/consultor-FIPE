package com.nexuscar.system.nexuscarsystem.domain.service;

import com.nexuscar.system.nexuscarsystem.domain.DTO.usuario.RegisterUserDTO;
import com.nexuscar.system.nexuscarsystem.domain.DTO.usuario.LoginDTO;
import com.nexuscar.system.nexuscarsystem.domain.DTO.usuario.TokenResponseDTO;
import com.nexuscar.system.nexuscarsystem.domain.entity.usuario.UsuarioEntity;
import com.nexuscar.system.nexuscarsystem.domain.entity.usuario.UsuarioRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService implements UserDetailsService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private TokenService tokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return repository.findByEmail(email);
    }

    @Transactional
    public void register(RegisterUserDTO dto){

        if(repository.findByEmail(dto.email()) != null){
            throw new RuntimeException("O email já está em uso.");
        }

        String senhaEncriptada = new BCryptPasswordEncoder().encode(dto.senha());

        var usuario = new UsuarioEntity(dto.nome(), dto.email(), senhaEncriptada, dto.telefone(), dto.cpf(), dto.endereco(), dto.role());
        repository.save(usuario);
    }

    public TokenResponseDTO login(LoginDTO dto){

        var loginESenha = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        var autenticacao = authenticationManager.authenticate(loginESenha);

        var token = tokenService.gerarToken((UsuarioEntity) autenticacao.getPrincipal());
        return new TokenResponseDTO(token);
    }
}
