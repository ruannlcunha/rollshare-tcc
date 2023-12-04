package br.edu.ifsul.api.mapper;

import br.edu.ifsul.api.domain.Categoria;
import br.edu.ifsul.api.domain.Usuario;
import br.edu.ifsul.api.domain.UsuarioCategoria;

public class UsuarioCategoriaMapper {

    public static UsuarioCategoria toEntity(Usuario usuario, Categoria categoria) {
        return UsuarioCategoria.builder()
                .usuario(usuario)
                .categoria(categoria)
                .ativo(true)
                .build();
    }


}
