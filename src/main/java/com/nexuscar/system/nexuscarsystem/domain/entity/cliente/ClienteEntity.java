package com.nexuscar.system.nexuscarsystem.domain.entity.cliente;

import com.nexuscar.system.nexuscarsystem.domain.DTO.cliente.CadastrarClienteDTO;
import com.nexuscar.system.nexuscarsystem.domain.DTO.cliente.EnderecoDTO;
import jakarta.persistence.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="clientes")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ClienteEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Email
    private String email;

    private long telefone;

    private long cpf;

    @Embedded
    @Valid
    private Endereco endereco;

    public ClienteEntity(@Valid CadastrarClienteDTO dto) {

        this.nome = dto.nome();
        this.email = dto.email();
        this.telefone = dto.telefone();
        this.cpf = dto.telefone();
        this.endereco = new Endereco(dto.endereco());

    }
}
