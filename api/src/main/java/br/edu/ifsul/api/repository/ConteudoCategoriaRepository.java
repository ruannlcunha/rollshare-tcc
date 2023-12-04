package br.edu.ifsul.api.repository;

import br.edu.ifsul.api.domain.ConteudoCategoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConteudoCategoriaRepository extends JpaRepository<ConteudoCategoria, Long> {

    ConteudoCategoria findByCategoriaNomeAndConteudoId(String nome, Long conteudoId);

    boolean existsByCategoriaNomeAndConteudoId(String nome, Long conteudoId);

    List<ConteudoCategoria> findByConteudoIdAndAtivo(Long conteudoId, boolean ativo);

    List<ConteudoCategoria> findByConteudoId(Long conteudoId);
}
