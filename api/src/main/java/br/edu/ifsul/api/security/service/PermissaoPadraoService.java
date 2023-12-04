package br.edu.ifsul.api.security.service;

import br.edu.ifsul.api.security.domain.Permissao;
import org.springframework.stereotype.Service;

import static br.edu.ifsul.api.security.domain.Funcao.USUARIO;

@Service
public class PermissaoPadraoService {

    public Permissao get() {
        Permissao permissao = new Permissao();
        permissao.setFuncao(USUARIO);
        return permissao;
    }

}
