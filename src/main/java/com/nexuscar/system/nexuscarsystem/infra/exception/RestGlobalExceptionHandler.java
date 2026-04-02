package com.nexuscar.system.nexuscarsystem.infra.exception;

import com.nexuscar.system.nexuscarsystem.infra.exception.customExceptions.UserNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.security.sasl.AuthenticationException;


@RestControllerAdvice
public class RestGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    private ResponseEntity<RestErrorMessage> userNotFoundHandler(UserNotFoundException ex){
        var response = new RestErrorMessage(HttpStatus.NOT_FOUND, "Usuário não encontrado.");

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
    }
}
