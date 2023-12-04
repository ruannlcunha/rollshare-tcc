package br.edu.ifsul.api.service.core;

import br.edu.ifsul.api.domain.UsuarioCategoria;
import br.edu.ifsul.api.repository.UsuarioCategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlterarUsuarioCategoriaService {

    @Autowired
    private UsuarioCategoriaRepository usuarioCategoriaRepository;

    public void ativar(UsuarioCategoria usuarioCategoria) {
        usuarioCategoria.setAtivo(true);

        usuarioCategoriaRepository.save(usuarioCategoria);
    }

    public void remover(UsuarioCategoria usuarioCategoria) {
        usuarioCategoria.setAtivo(false);

        usuarioCategoriaRepository.save(usuarioCategoria);
    }

}
