package br.edu.ifsul.api.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
public class UsuarioRequest {

    @NotBlank
    @Size(max = 255)
    private String nomeDeUsuario;

    @NotBlank
    @Size(max = 255)
    private String apelido;

    @Email
    @NotNull
    @Size(max = 255)
    private String email;

    @Size(max = 128)
    @NotBlank
    private String senha;

    @Size(max = 128)
    @NotBlank
    private String confirmacaoDeSenha;

    private String imagemPerfil;

    private String imagemCapa;

}
