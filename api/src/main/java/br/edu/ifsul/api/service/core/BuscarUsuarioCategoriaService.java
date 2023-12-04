package br.edu.ifsul.api.service.core;

import br.edu.ifsul.api.domain.UsuarioCategoria;
import br.edu.ifsul.api.repository.UsuarioCategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class BuscarUsuarioCategoriaService {

    @Autowired
    private UsuarioCategoriaRepository usuarioCategoriaRepository;

    public UsuarioCategoria buscar(String nome, Long usuarioId) {
        return usuarioCategoriaRepository.findByCategoriaNomeAndUsuarioId(nome, usuarioId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Este usuário não possui esta categoria"));
    }

    public boolean existe(String nome, Long usuarioId) {
        return usuarioCategoriaRepository.existsByCategoriaNomeAndUsuarioId(nome, usuarioId);
    }

}
