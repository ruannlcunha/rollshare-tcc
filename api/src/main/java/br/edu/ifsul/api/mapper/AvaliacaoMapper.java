package br.edu.ifsul.api.mapper;

import br.edu.ifsul.api.controller.request.AvaliacaoRequest;
import br.edu.ifsul.api.controller.response.AvaliacaoResponse;
import br.edu.ifsul.api.domain.Avaliacao;

public class AvaliacaoMapper {

    public static Avaliacao toEntity(AvaliacaoRequest request) {
        Avaliacao avaliacao = new Avaliacao();
        avaliacao.setDescricao(request.getDescricao());
        avaliacao.setNota(request.getNota());
        return avaliacao;
    }

    public static AvaliacaoResponse toResponse(Avaliacao avaliacao, String imagemUsuario) {
        return AvaliacaoResponse.builder()
                .avaliacaoId(avaliacao.getId())
                .descricao(avaliacao.getDescricao())
                .nota(avaliacao.getNota())
                .usuarioId(avaliacao.getUsuario().getId())
                .usuarioNome(avaliacao.getUsuario().getApelido())
                .usuarioImagem(imagemUsuario)
                .dataInicio(avaliacao.getDataInicio())
                .build();
    }

}
