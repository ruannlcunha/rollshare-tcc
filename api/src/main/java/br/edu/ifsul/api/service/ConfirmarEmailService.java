package br.edu.ifsul.api.service;

import br.edu.ifsul.api.controller.request.TokenRequest;
import br.edu.ifsul.api.domain.Usuario;
import br.edu.ifsul.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class ConfirmarEmailService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public void confirmarEmail(TokenRequest tokenRequest) {
        Usuario usuario = usuarioRepository.findByTokenSeguranca(tokenRequest.getToken())
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Este código não existe."));

        usuario.setTokenSeguranca(null);
        usuario.setEmailValido(true);

        usuarioRepository.save(usuario);
    }

}
