package br.edu.ifsul.api.mapper;

import br.edu.ifsul.api.controller.request.DenunciaRequest;
import br.edu.ifsul.api.controller.response.DenunciaResponse;
import br.edu.ifsul.api.domain.Denuncia;

public class DenunciaMapper {

    public static Denuncia toEntity(DenunciaRequest request) {
        Denuncia denuncia = new Denuncia();
        denuncia.setMotivo(request.getMotivo());
        return denuncia;
    }

    public static DenunciaResponse toResponse(Denuncia denuncia,
                                              String imagemPerfil,
                                              String[] categorias,
                                              String[] imagens,
                                              Long insigniaId) {
        return DenunciaResponse.builder()
                .denunciaId(denuncia.getId())
                .motivo(denuncia.getMotivo())
                .id(denuncia.getConteudo().getId())
                .descricao(denuncia.getConteudo().getDescricao())
                .usuarioNome(denuncia.getConteudo().getUsuario().getApelido())
                .usuarioId(denuncia.getConteudo().getUsuario().getId())
                .dataInicio(denuncia.getConteudo().getDataInicio())
                .categorias(categorias)
                .usuarioImagem(imagemPerfil)
                .imagens(imagens)
                .foiAlterado(denuncia.getConteudo().isFoiAlterado())
                .insigniaId(insigniaId)
                .build();
    }

}
