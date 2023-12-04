package br.edu.ifsul.api.domain.enums;

import lombok.Getter;

@Getter
public enum RollPointsEnum {
    ROLLPOINTS_AVALIACAO(5), ROLLPOINTS_CONTEUDO(10);

    private int quantidade;

    private RollPointsEnum(int quantidade) {
        this.quantidade = quantidade;
    }
}
