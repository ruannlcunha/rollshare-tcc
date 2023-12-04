package br.edu.ifsul.api.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter @Setter
public class AlterarConteudoRequest {

    @NotBlank
    @Size(max = 255)
    private String descricao;

    @NotNull
    private String[] categorias;

}
