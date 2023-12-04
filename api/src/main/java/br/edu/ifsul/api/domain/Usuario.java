package br.edu.ifsul.api.domain;

import br.edu.ifsul.api.security.domain.Permissao;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Entity
@Getter @Setter @EqualsAndHashCode(of = "id") @ToString(of = "id")
public class Usuario {


    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String nomeDeUsuario;

    private String apelido;

    private String email;

    private String senha;

    private String imagemPerfil;

    private String imagemCapa;

    private Integer rollpoints;

    private String tokenSeguranca;

    private boolean emailValido;

    private boolean ativo;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Permissao> permissoes = new ArrayList<>();

    @OneToMany(mappedBy = "alvo")
    private List<PerfilFavorito> favoritosAlvo = new ArrayList<>();

    @OneToMany(mappedBy = "dono")
    private List<PerfilFavorito> favoritosDono = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<Avaliacao> avaliacoes = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<Conteudo> conteudos = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<Denuncia> denuncias = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<UsuarioCosmetico> usuarioCosmeticos = new ArrayList<>();

    @OneToMany(mappedBy = "usuario")
    private List<UsuarioCategoria> usuarioCategorias = new ArrayList<>();

    public void adicionarPermissao(Permissao permissao) {
        this.permissoes.add(permissao);
        permissao.setUsuario(this);

    }

    public void adicionarFavoritoDono(PerfilFavorito perfilFavorito) {
        this.favoritosDono.add(perfilFavorito);
        perfilFavorito.setDono(this);
    }

    public void adicionarFavoritoAlvo(PerfilFavorito perfilFavoritado) {
        this.favoritosAlvo.add(perfilFavoritado);
        perfilFavoritado.setAlvo(this);
    }

    public void adicionarAvaliacao(Avaliacao avaliacao) {
        this.avaliacoes.add(avaliacao);
        avaliacao.setUsuario(this);
    }

    public void adicionarCosmeticos(UsuarioCosmetico usuarioCosmetico) {
        this.usuarioCosmeticos.add(usuarioCosmetico);
        usuarioCosmetico.setUsuario(this);
    }

    public void adicionarCategoria(UsuarioCategoria usuarioCategoria) {
        this.usuarioCategorias.add(usuarioCategoria);
        usuarioCategoria.setUsuario(this);
    }

    public void adicionarDenuncia(Denuncia denuncia) {
        this.denuncias.add(denuncia);
        denuncia.setUsuario(this);
    }

    public void adicionarConteudo(Conteudo conteudo) {
        this.conteudos.add(conteudo);
        conteudo.setUsuario(this);
    }

}
