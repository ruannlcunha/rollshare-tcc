package br.edu.ifsul.api.controller.response;

import lombok.*;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class UsuarioDetalhadoResponse {

    private Long id;

    private String nomeDeUsuario;

    private String apelido;

    private String email;

    private Integer rollPoints;

    private boolean foiFavoritado;

    private Integer quantidadeDeConteudos;

    private Integer quantidadeDeFavoritos;

    private Integer fundoId;

    private Integer insigniaId;

    private String imagemPerfil;

    private String imagemCapa;


}
