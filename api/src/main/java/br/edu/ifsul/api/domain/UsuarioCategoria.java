package br.edu.ifsul.api.domain;

import lombok.*;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id") @ToString(of = "id")
public class UsuarioCategoria {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private boolean ativo;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "categoria_id")
    private Categoria categoria;

}
