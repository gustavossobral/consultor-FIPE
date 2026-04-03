package com.nexuscar.system.nexuscarsystem.service;

import com.nexuscar.system.nexuscarsystem.dto.user.RegisterUserDTO;
import com.nexuscar.system.nexuscarsystem.dto.user.LoginDTO;
import com.nexuscar.system.nexuscarsystem.dto.user.TokenResponseDTO;
import com.nexuscar.system.nexuscarsystem.model.UserEntity;
import com.nexuscar.system.nexuscarsystem.repository.UserRepository;
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
    private UserRepository repository;

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

        String senhaEncriptada = new BCryptPasswordEncoder().encode(dto.password());

        var usuario = new UserEntity(dto.name(), dto.email(), senhaEncriptada, dto.phone(), dto.cpf(), dto.address(), dto.role());
        repository.save(usuario);
    }

    public TokenResponseDTO login(LoginDTO dto){

        var loginESenha = new UsernamePasswordAuthenticationToken(dto.email(), dto.password());
        var autenticacao = authenticationManager.authenticate(loginESenha);

        var token = tokenService.gerarToken((UserEntity) autenticacao.getPrincipal());
        return new TokenResponseDTO(token);
    }
}
