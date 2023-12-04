package br.edu.ifsul.api.controller.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SenhaResponse {

    private Long id;

    private String nomeDeUsuario;

    private String apelido;

    private String imagemDePerfil;

}
