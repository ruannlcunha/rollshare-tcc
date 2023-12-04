package br.edu.ifsul.api.domain.enums;

import lombok.Getter;

@Getter
public enum CategoriasEnum {
    FICHA("Ficha"), PERSONAGEM("Personagem"), ARTE("Arte"), HISTORIA("História"),
    MONSTRO("Monstro"), SUPLEMENTO("Suplemento"), OUTRO("Outro");

    private String nome;

    private CategoriasEnum(String nome) {
        this.nome = nome;
    }
}
