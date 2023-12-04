package br.edu.ifsul.api.controller.response;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class CategoriasFavoritasResponse {

    private Long usuarioId;

    private CategoriaResponse[] categorias;

}
