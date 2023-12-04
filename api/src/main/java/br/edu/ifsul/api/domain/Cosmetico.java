package br.edu.ifsul.api.domain;

import br.edu.ifsul.api.domain.enums.CosmeticoEnum;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.EnumType.STRING;
import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id") @ToString(of = "id")
public class Cosmetico {


    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String nome;

    @Enumerated(STRING)
    private CosmeticoEnum tipo;

    private Integer custo;

    @OneToMany(mappedBy = "cosmetico")
    private List<UsuarioCosmetico> usuarioCosmeticos = new ArrayList<>();

    public void adicionarUsuario(UsuarioCosmetico usuarioCosmetico) {
        this.usuarioCosmeticos.add(usuarioCosmetico);
        usuarioCosmetico.setCosmetico(this);
    }

}
