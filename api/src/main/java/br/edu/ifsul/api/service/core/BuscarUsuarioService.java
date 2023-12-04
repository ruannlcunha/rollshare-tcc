package br.edu.ifsul.api.service.core;

import br.edu.ifsul.api.domain.Usuario;
import br.edu.ifsul.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@Service
public class BuscarUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public Usuario porId(Long id) {
        return usuarioRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Usuário não existe"));
    }



    public Usuario porTokenSeguranca(String tokenSeguranca) {
        return usuarioRepository.findByTokenSeguranca(tokenSeguranca)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Usuário não existe"));
    }

    public Usuario porEmail(String email) {
        return usuarioRepository.findByEmail(email)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Usuário não existe"));
    }
}
