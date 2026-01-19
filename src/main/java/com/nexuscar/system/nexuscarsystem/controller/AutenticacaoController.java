package com.nexuscar.system.nexuscarsystem.controller;

import com.nexuscar.system.nexuscarsystem.domain.DTO.usuario.CadastrarUsuarioDTO;
import com.nexuscar.system.nexuscarsystem.domain.DTO.usuario.CadastrarUsuarioResponseDTO;
import com.nexuscar.system.nexuscarsystem.domain.DTO.usuario.LoginDTO;
import com.nexuscar.system.nexuscarsystem.domain.DTO.usuario.TokenResponseDTO;
import com.nexuscar.system.nexuscarsystem.domain.entity.usuario.UsuarioEntity;
import com.nexuscar.system.nexuscarsystem.domain.entity.usuario.UsuarioRepository;
import com.nexuscar.system.nexuscarsystem.domain.service.TokenService;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("/auth")
public class AutenticacaoController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TokenService tokenService;

    @PostMapping("/cadastrar")
    @Transactional
    @PreAuthorize("hasAnyRole('ADMIN', 'VENDEDOR')")
    public ResponseEntity<CadastrarUsuarioResponseDTO> cadastrarUsuario(@RequestBody @Valid CadastrarUsuarioDTO dto, UriComponentsBuilder uriBuilder){

        if(repository.findByEmail(dto.email()) != null){
            return ResponseEntity.badRequest().build();
        }

        String senhaEncriptada = new BCryptPasswordEncoder().encode(dto.senha());

        var usuario = new UsuarioEntity(dto.nome(), dto.email(), senhaEncriptada, dto.telefone(), dto.cpf(), dto.endereco(), dto.role());
        repository.save(usuario);

        var uri = uriBuilder.path("/auth/cadastro/{id}").buildAndExpand(usuario.getId()).toUri();
        var response = new CadastrarUsuarioResponseDTO(usuario.getId(),usuario.getNome(), usuario.getEmail(), usuario.getTelefone(), usuario.getRole());

       return ResponseEntity.created(uri).body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponseDTO> login(@RequestBody @Valid LoginDTO dto){
        var loginESenha = new UsernamePasswordAuthenticationToken(dto.email(), dto.senha());
        var autenticacao = authenticationManager.authenticate(loginESenha);

        var token = tokenService.gerarToken((UsuarioEntity) autenticacao.getPrincipal());
        var response = new TokenResponseDTO(token);

        return ResponseEntity.ok(response);
    }


}
