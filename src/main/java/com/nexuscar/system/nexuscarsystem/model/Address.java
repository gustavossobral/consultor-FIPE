package com.nexuscar.system.nexuscarsystem.model;

import com.nexuscar.system.nexuscarsystem.dto.user.AddressDTO;
import jakarta.persistence.Embeddable;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    private String street;

    private Short number;

    private String complement;

    private String neighborhood;

    private String city;

    private String uf;

    private String cep;

    public Address(@Valid AddressDTO dto){

        this.street = dto.street();
        this.number = dto.number();
        this.complement = dto.complement();
        this.neighborhood = dto.neighborhood();
        this.city = dto.city();
        this.uf = dto.uf();
        this.cep = dto.cep();

    }

}
