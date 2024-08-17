package com.nail.design.servicos.dto;

import jakarta.validation.constraints.NotEmpty;

public class CreateEditServicoDto {

    @NotEmpty
    public String nome;

    @NotEmpty
    public String imagem;

    public CreateEditServicoDto() {
        // Empty Constructor
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

}
