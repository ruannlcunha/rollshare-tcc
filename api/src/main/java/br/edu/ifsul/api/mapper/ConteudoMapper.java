package br.edu.ifsul.api.mapper;

import br.edu.ifsul.api.controller.request.ConteudoRequest;
import br.edu.ifsul.api.controller.response.ConteudoResponse;
import br.edu.ifsul.api.domain.Conteudo;

public class ConteudoMapper {

    public static Conteudo toEntity(ConteudoRequest request) {
        return Conteudo.builder()
                .descricao(request.getDescricao())
                .build();
    }

    public static ConteudoResponse toResponse(Conteudo conteudo,
                                              String imagemPerfil,
                                              String[] categorias,
                                              Integer insigniaId,
                                              String[] imagens) {
        return ConteudoResponse.builder()
                .id(conteudo.getId())
                .descricao(conteudo.getDescricao())
                .usuarioNome(conteudo.getUsuario().getApelido())
                .usuarioId(conteudo.getUsuario().getId())
                .dataInicio(conteudo.getDataInicio())
                .categorias(categorias)
                .usuarioImagem(imagemPerfil)
                .imagens(imagens)
                .foiAlterado(conteudo.isFoiAlterado())
                .insigniaId(insigniaId)
                .build();
    }

}
