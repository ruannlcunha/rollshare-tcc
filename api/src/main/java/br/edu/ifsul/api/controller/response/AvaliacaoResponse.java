package br.edu.ifsul.api.controller.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter @Setter
public class AvaliacaoResponse {

    private Long avaliacaoId;

    private String descricao;

    private int nota;

    private Long usuarioId;

    private String usuarioNome;

    private LocalDateTime dataInicio;

    private String usuarioImagem;

}
