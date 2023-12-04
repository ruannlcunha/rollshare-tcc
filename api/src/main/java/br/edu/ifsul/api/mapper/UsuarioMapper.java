package br.edu.ifsul.api.mapper;

import br.edu.ifsul.api.controller.request.UsuarioRequest;
import br.edu.ifsul.api.controller.response.UsuarioMinimoResponse;
import br.edu.ifsul.api.controller.response.UsuarioResponse;
import br.edu.ifsul.api.domain.Usuario;

public class UsuarioMapper {

    public static Usuario toEntity(UsuarioRequest request) {
        Usuario entity = new Usuario();
        entity.setApelido(request.getApelido());
        entity.setNomeDeUsuario(request.getNomeDeUsuario().toLowerCase());
        entity.setEmail(request.getEmail());
        return entity;
    }

    public static UsuarioResponse toResponse(Usuario usuario, String imagemPerfil, Long insigniaId) {
        UsuarioResponse response = new UsuarioResponse();
        response.setId(usuario.getId());
        response.setNomeDeUsuario(usuario.getNomeDeUsuario());
        response.setApelido(usuario.getApelido());
        response.setImagemDePerfil(imagemPerfil);
        response.setInsigniaId(insigniaId);
        return response;
    }

    public static UsuarioMinimoResponse toResponseMinimo(Usuario entity, boolean isAdmin) {
        UsuarioMinimoResponse response = new UsuarioMinimoResponse();
        response.setId(entity.getId());
        response.setIsAdmin(isAdmin);
        return response;
    }
}
