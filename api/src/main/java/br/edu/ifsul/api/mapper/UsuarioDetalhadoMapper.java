package br.edu.ifsul.api.mapper;

import br.edu.ifsul.api.controller.response.UsuarioDetalhadoResponse;
import br.edu.ifsul.api.domain.Usuario;

import java.util.stream.Collectors;

public class UsuarioDetalhadoMapper {

    public static UsuarioDetalhadoResponse toResponse(Usuario usuario,
                                                      String imagemPerfilUri,
                                                      String imagemCapaUri,
                                                      Integer fundoId,
                                                      Integer insigniaId,
                                                      boolean foiFavoritado
                                                      ) {
        return UsuarioDetalhadoResponse.builder()
                .id(usuario.getId())
                .nomeDeUsuario(usuario.getNomeDeUsuario())
                .apelido(usuario.getApelido())
                .email(usuario.getEmail())
                .imagemPerfil(imagemPerfilUri)
                .imagemCapa(imagemCapaUri)
                .rollPoints(usuario.getRollpoints())
                .quantidadeDeConteudos(usuario.getConteudos()
                        .stream().filter(obj->obj.isAtivo()).collect(Collectors.toList()).size())
                .quantidadeDeFavoritos(usuario.getFavoritosAlvo()
                        .stream().filter(obj->obj.isAtivo()).collect(Collectors.toList()).size())
                .fundoId(fundoId)
                .insigniaId(insigniaId)
                .foiFavoritado(foiFavoritado)
                .build();
    }
}