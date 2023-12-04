package br.edu.ifsul.api.repository;

import br.edu.ifsul.api.domain.Avaliacao;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AvaliacaoRepository extends JpaRepository<Avaliacao, Long> {
    Page<Avaliacao> findByConteudoIdAndAtivo(Long conteudoId, boolean ativo, Pageable pageable);

    Optional<Avaliacao> findByIdAndAtivo(Long id, boolean ativo);
}
