package br.edu.ifsul.api.domain;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static javax.persistence.GenerationType.IDENTITY;

@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Getter @Setter
@EqualsAndHashCode(of = "id") @ToString(of = "id")
public class Conteudo {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    private Long id;

    private String descricao;

    private boolean foiAlterado;

    private boolean ativo;

    private LocalDateTime dataInicio;

    @ManyToOne
    @JoinColumn(name = "usuario_id")
    private Usuario usuario;

    @OneToMany(mappedBy = "conteudo")
    private List<ConteudoImagem> imagens = new ArrayList<>();

    @OneToMany(mappedBy = "conteudo")
    private List<ConteudoCategoria> categorias = new ArrayList<>();

    @OneToMany(mappedBy = "conteudo")
    private List<Avaliacao> avaliacoes = new ArrayList<>();

    @OneToMany(mappedBy = "conteudo")
    private List<Denuncia> denuncias = new ArrayList<>();

    public void adicionarAvaliacao(Avaliacao avaliacao) {
        this.avaliacoes.add(avaliacao);
        avaliacao.setConteudo(this);
    }

    public void adicionarCategoria(ConteudoCategoria conteudoCategoria) {
        this.categorias.add(conteudoCategoria);
        conteudoCategoria.setConteudo(this);
    }

    public void adicionarImagem(ConteudoImagem conteudoImagem) {
        this.imagens.add(conteudoImagem);
        conteudoImagem.setConteudo(this);
    }

    public void adicionarDenuncia(Denuncia denuncia) {
        this.denuncias.add(denuncia);
        denuncia.setConteudo(this);
    }
}
