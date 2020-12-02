package com.example.af_poo.dto;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class ClienteDTO {
    
    @NotBlank(message = "Endereço obrigatório!")
    @Length(min = 4, max = 40, message = "Endereço deve ter no mínimo 4 e no máximo 40 caracteres!")
    private String endereco;

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }
}
