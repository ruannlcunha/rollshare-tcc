package br.edu.ifsul.api.mapper;

import br.edu.ifsul.api.controller.response.FavoritoResponse;
import br.edu.ifsul.api.domain.PerfilFavorito;

public class FavoritoMapper {

    public static FavoritoResponse toResponse(PerfilFavorito perfilFavorito) {
        return FavoritoResponse.builder()
                .id(perfilFavorito.getId())
                .ativo(perfilFavorito.isAtivo())
                .favoritoId(perfilFavorito.getAlvo().getId())
                .favoritoNome(perfilFavorito.getAlvo().getNomeDeUsuario())
                .usuarioId(perfilFavorito.getDono().getId())
                .usuarioNome(perfilFavorito.getDono().getNomeDeUsuario())
                .build();
    }

}
