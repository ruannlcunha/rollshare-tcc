package br.edu.ifsul.api.controller.response;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CosmeticoResponse {

    private Long id;

    private String nome;

    private String tipo;

    private boolean emUso;

    private Integer custo;

}
