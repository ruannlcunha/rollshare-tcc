package br.edu.ifsul.api.validator;

import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Component
public class SaldoValidator {

    public void validar(Integer custo, Integer saldo){
        if(saldo<custo){
            throw new ResponseStatusException(BAD_REQUEST, "Você não possui Rollpoints o suficiente");
        };
    }

}
