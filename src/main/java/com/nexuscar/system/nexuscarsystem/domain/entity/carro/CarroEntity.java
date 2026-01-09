package com.nexuscar.system.nexuscarsystem.domain.entity.carro;

import com.nexuscar.system.nexuscarsystem.domain.DTO.carro.cadastro.CadastrarCarroDTO;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.ZoneId;

@Entity
@Table(name="carros")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = {"id"})
public class CarroEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String modelo;

    private String marca;

    private short anoFabricacao;

    private int quilometragem;

    private byte qtdDonos;

    private BigDecimal preco;

    private String cor;

    private String combustivel;

    private String observacoes;

    private LocalDateTime dataDeCadastro;

    @Enumerated(EnumType.STRING)
    private Status status;

    public CarroEntity(CadastrarCarroDTO dto){

        this.modelo = dto.modelo();
        this.marca = dto.marca();
        this.anoFabricacao = dto.anoFabricacao();
        this.quilometragem = dto.quilometragem();
        this.qtdDonos = dto.qtdDonos();
        this.preco = dto.preco();
        this.cor = dto.cor();
        this.combustivel = dto.combustivel();
        this.observacoes = dto.observacoes();
        this.dataDeCadastro = LocalDateTime.now(ZoneId.of("America/Sao_Paulo"));

    }


}
