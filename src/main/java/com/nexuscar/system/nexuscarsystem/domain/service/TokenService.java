package com.nexuscar.system.nexuscarsystem.domain.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.nexuscar.system.nexuscarsystem.domain.entity.usuario.UsuarioEntity;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class TokenService {

    @Value("${nexuscarsystem.security.jwt.token.secret}")
    private String secret;

    public String gerarToken(UsuarioEntity usuario){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try {
            String token = JWT.create()
                    .withIssuer("nexuscarsystem")
                    .withSubject(usuario.getEmail())
                    .withExpiresAt(tempoDeExpiracao())
                    .sign(algorithm);
            return token;
        } catch (JWTCreationException exception){
            throw new RuntimeException("Erro na geração do token JWT");
        }
    }

    public String validarToken(String token){
        Algorithm algorithm = Algorithm.HMAC256(secret);
        try {
            return JWT.require(algorithm)
                    .withIssuer("nexuscarsystem")
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            throw new RuntimeException("Erro na validação do token JWT");
        }
    }


    public Instant tempoDeExpiracao(){
        return LocalDateTime.now().plusHours(1).toInstant(ZoneOffset.ofHours(-3));
    }
}

