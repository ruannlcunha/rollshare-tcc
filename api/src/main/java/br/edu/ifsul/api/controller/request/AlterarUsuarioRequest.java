package br.edu.ifsul.api.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class AlterarUsuarioRequest {

    @NotBlank
    @Size(max = 255)
    private String nomeDeUsuario;

    @NotBlank
    @Size(max = 255)
    private String apelido;

    @NotBlank
    private String email;

    @NotNull
    private String imagemDePerfil;

    @NotNull
    private String imagemDeCapa;

}
