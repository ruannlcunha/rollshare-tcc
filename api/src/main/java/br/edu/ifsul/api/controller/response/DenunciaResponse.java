package br.edu.ifsul.api.controller.response;

import lombok.*;

import java.time.LocalDateTime;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class DenunciaResponse {

    private Long denunciaId;

    private String motivo;

    private Long id;

    private String descricao;

    private String[] categorias;

    private String usuarioNome;

    private Long usuarioId;

    private LocalDateTime dataInicio;

    private boolean foiAlterado;

    private Long insigniaId;

    private String usuarioImagem;

    private String[] imagens;

}
