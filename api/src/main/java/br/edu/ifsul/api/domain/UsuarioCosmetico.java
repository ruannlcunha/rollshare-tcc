package br.edu.ifsul.api.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id") @ToString(of = "id")
public class UsuarioCosmetico {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private boolean emUso;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "cosmetico_id")
    private Cosmetico cosmetico;

}
