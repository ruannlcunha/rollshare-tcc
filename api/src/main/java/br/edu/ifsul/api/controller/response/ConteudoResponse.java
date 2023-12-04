package br.edu.ifsul.api.controller.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ConteudoResponse {

    private Long id;

    private String descricao;

    private String[] categorias;

    private String usuarioNome;

    private Long usuarioId;

    private LocalDateTime dataInicio;

    private Integer insigniaId;

    private boolean foiAlterado;

    private String usuarioImagem;

    private String[] imagens;

}
