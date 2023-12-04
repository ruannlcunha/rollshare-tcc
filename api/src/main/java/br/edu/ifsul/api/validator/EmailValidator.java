package br.edu.ifsul.api.validator;

import br.edu.ifsul.api.domain.Usuario;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


@Component
public class EmailValidator {

    public void validar(Usuario usuario){
        if(!usuario.isEmailValido()){
            throw new ResponseStatusException(BAD_REQUEST, "O email não foi confirmado, acesse seu email.");
        };
    }

}
