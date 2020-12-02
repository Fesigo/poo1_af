package com.example.af_poo.model;

import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.Length;

public class Veiculo {
    
    private int codigo;
    
    @NotBlank(message = "Modelo obrigatório!")
    @Length(min = 4, max = 40, message = "O Modelo deve ter no mínimo 4 e no máximo 40 caracteres!")
    private String modelo;
    
    private Double valorDiaria;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Double getValorDiaria() {
        return valorDiaria;
    }

    public void setValorDiaria(Double valorDiaria) {
        this.valorDiaria = valorDiaria;
    }

}
