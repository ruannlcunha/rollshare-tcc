package br.edu.ifsul.api.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter @Setter
public class ResolverDenunciaRequest {

    @NotNull
    private Boolean remover;

}
