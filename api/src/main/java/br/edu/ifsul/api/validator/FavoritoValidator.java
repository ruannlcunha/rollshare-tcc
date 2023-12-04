package br.edu.ifsul.api.validator;

import br.edu.ifsul.api.domain.Usuario;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;


@Component
public class FavoritoValidator {

    public void validar(Usuario dono, Usuario alvo){
        if(dono.equals(alvo)){
            throw new ResponseStatusException(BAD_REQUEST, "Você não pode favoritar você mesmo.");
        };
    }

}
