package br.edu.ifsul.api.controller.response;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class CategoriaResponse {

    private Long id;

    private String nome;

    private boolean ativo;

}
