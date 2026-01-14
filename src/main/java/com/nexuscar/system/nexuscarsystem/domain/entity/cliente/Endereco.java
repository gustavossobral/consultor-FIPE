package com.nexuscar.system.nexuscarsystem.domain.entity.cliente;

import com.nexuscar.system.nexuscarsystem.domain.DTO.cliente.EnderecoDTO;
import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Endereco {

    private String logradouro;

    private short numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String uf;

    private int cep;

    public Endereco(@Valid EnderecoDTO dto){

        this.logradouro = dto.logradouro();
        this.numero = dto.numero();
        this.complemento = dto.complemento();
        this.bairro = dto.bairro();
        this.cidade = dto.cidade();
        this.uf = dto.uf();
        this.cep = dto.cep();

    }

}
