package br.edu.ifsul.api.mapper;

import br.edu.ifsul.api.controller.response.CategoriaResponse;
import br.edu.ifsul.api.domain.UsuarioCategoria;

public class CategoriaMapper {

    public static CategoriaResponse toResponse(UsuarioCategoria usuarioCategoria) {
        return CategoriaResponse.builder()
                .id(usuarioCategoria.getCategoria().getId())
                .nome(usuarioCategoria.getCategoria().getNome())
                .ativo(usuarioCategoria.isAtivo())
                .build();
    }

}
