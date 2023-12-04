package br.edu.ifsul.api.controller.response;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class FavoritoResponse {

    private Long id;

    private Long usuarioId;

    private Long favoritoId;

    private String usuarioNome;

    private String favoritoNome;

    private boolean ativo;

}
