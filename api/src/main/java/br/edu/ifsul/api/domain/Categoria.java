package br.edu.ifsul.api.domain;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id") @ToString(of = "id")
public class Categoria {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String nome;

    @OneToMany(mappedBy = "categoria")
    private List<UsuarioCategoria> usuarioCategoria = new ArrayList<>();

    @OneToMany(mappedBy = "categoria")
    private List<ConteudoCategoria> conteudoCategoria = new ArrayList<>();

    public void adicionarUsuario(UsuarioCategoria usuarioCategoria) {
        this.usuarioCategoria.add(usuarioCategoria);
        usuarioCategoria.setCategoria(this);
    }

    public void adicionarConteudo(ConteudoCategoria conteudoCategoria) {
        this.conteudoCategoria.add(conteudoCategoria);
        conteudoCategoria.setCategoria(this);
    }

}
