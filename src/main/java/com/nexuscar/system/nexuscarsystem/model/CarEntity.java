package com.nexuscar.system.nexuscarsystem.model;

import com.nexuscar.system.nexuscarsystem.model.enums.Status;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="carros")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
@EqualsAndHashCode(of = {"id"})
public class CarEntity {

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

    private LocalDateTime inicioReserva;

    @Enumerated(EnumType.STRING)
    private Status status = Status.DISPONIVEL;



}
