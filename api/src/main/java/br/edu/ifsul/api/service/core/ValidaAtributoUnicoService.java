package br.edu.ifsul.api.service.core;

import br.edu.ifsul.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


@Service
public class ValidaAtributoUnicoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void validarEmail(String email){
        if(usuarioRepository.existsByEmail(email)){
            throw new ResponseStatusException(BAD_REQUEST, "Esse email já existe");
        };
    }

    public void validarNome(String nome){
        if(usuarioRepository.existsByNomeDeUsuario(nome)){
            throw new ResponseStatusException(BAD_REQUEST, "Esse nome de usuário já existe");
        };
    }

}
