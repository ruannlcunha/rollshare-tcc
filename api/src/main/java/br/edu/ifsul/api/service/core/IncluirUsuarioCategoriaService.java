package br.edu.ifsul.api.service.core;

import br.edu.ifsul.api.domain.Categoria;
import br.edu.ifsul.api.domain.Usuario;
import br.edu.ifsul.api.domain.UsuarioCategoria;
import br.edu.ifsul.api.repository.UsuarioCategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.edu.ifsul.api.mapper.UsuarioCategoriaMapper.toEntity;

@Service
public class IncluirUsuarioCategoriaService {

    @Autowired
    private UsuarioCategoriaRepository usuarioCategoriaRepository;

    public UsuarioCategoria incluirUsuarioCategoria(Usuario usuario, Categoria categoria) {
        UsuarioCategoria usuarioCategoria = toEntity(usuario, categoria);

        usuarioCategoriaRepository.save(usuarioCategoria);
        return usuarioCategoria;
    }

}
