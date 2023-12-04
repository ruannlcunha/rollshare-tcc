package br.edu.ifsul.api.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
public class SenhaRequest {

    @Size(max = 128)
    @NotBlank
    private String senha;

    @Size(max = 128)
    @NotBlank
    private String confirmacaoDeSenha;

    @NotBlank
    private String tokenSeguranca;

}
