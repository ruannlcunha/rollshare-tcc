package br.edu.ifsul.api.controller.response;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class SolicitacaoResponse {

    private Long idAmizade;

    private Long idSolicitante;

    private String nomeSolicitante;

}
