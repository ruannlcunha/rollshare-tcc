package br.edu.ifsul.api.domain.enums;

import lombok.Getter;

@Getter
public enum CosmeticoEnum {
    FUNDO("Fundo"), INSIGNIA("Insignia");

    private String nome;

    private CosmeticoEnum(String nome) {
        this.nome = nome;
    }
}
