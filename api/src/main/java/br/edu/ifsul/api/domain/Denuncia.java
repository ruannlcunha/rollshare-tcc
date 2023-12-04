package br.edu.ifsul.api.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDateTime;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id") @ToString(of = "id")
public class Denuncia {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String motivo;

    private boolean ativo;

    private LocalDateTime dataInicio;

    @ManyToOne
    @JoinColumn(name = "conteudo_id")
    private Conteudo conteudo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

}
