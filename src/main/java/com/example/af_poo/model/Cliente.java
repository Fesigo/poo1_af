package com.example.af_poo.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;

public class Cliente {
    
    private int codigo;

    @NotBlank(message = "Nome obrigatório!")
    @Length(min = 4, max = 40, message = "Nome deve ter no mínimo 4 e no máximo 40 caracteres!")
    private String nome;

    @NotBlank(message = "Endereço obrigatório!")
    @Length(min = 4, max = 50, message = "Endereço deve ter no mínimo 4 e no máximo 50 caracteres!")
    private String endereco;
    
    @NotBlank(message = "CPF obrigatório!")
    @Size(min = 11, max = 14, message = "CPF inválido!")
    private String cpf;

    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

}
