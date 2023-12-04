package br.edu.ifsul.api.domain;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter @Setter
@EqualsAndHashCode(of = "id") @ToString(of = "id")
public class ConteudoImagem {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String dataUri;

    @ManyToOne
    @JoinColumn(name = "conteudo_id")
    private Conteudo conteudo;
}
