package br.edu.ifsul.api.repository;

import br.edu.ifsul.api.domain.ConteudoImagem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ConteudoImagemRepository extends JpaRepository<ConteudoImagem, Long> {

    List<ConteudoImagem> findByConteudoId(Long conteudoId);
}
