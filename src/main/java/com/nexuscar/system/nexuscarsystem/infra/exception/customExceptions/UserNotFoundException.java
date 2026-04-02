package com.nexuscar.system.nexuscarsystem.infra.exception.customExceptions;

import javax.security.sasl.AuthenticationException;

public class UserNotFoundException extends AuthenticationException {

    public UserNotFoundException(){
        super("Usuário ou senha inválidos.");
    }

    public UserNotFoundException(String mensagem) {
        super(mensagem);
    }

}
