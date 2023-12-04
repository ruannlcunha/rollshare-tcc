package br.edu.ifsul.api.service.core;

import br.edu.ifsul.api.domain.UsuarioCategoria;
import br.edu.ifsul.api.repository.UsuarioCategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AtivarUsuarioCategoriaService {

    @Autowired
    private UsuarioCategoriaRepository usuarioCategoriaRepository;

    @Autowired
    private BuscarUsuarioCategoriaService buscarUsuarioCategoriaService;

    public void remover(String nome, Long usuarioId) {
        UsuarioCategoria usuarioCategoria = buscarUsuarioCategoriaService.buscar(nome, usuarioId);

        usuarioCategoria.setAtivo(false);

        usuarioCategoriaRepository.save(usuarioCategoria);
    }

}
