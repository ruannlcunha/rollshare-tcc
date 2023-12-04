package br.edu.ifsul.api.controller.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.*;

@Getter @Setter
public class AvaliacaoRequest {

    @NotBlank
    @Size(max = 255)
    private String descricao;

    @NotNull
    @Min(1)
    @Max(5)
    private int nota;
}
