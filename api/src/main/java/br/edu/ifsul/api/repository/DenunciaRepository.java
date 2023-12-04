package br.edu.ifsul.api.repository;

import br.edu.ifsul.api.domain.Denuncia;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface DenunciaRepository extends JpaRepository<Denuncia, Long> {

    Optional<Denuncia> findByIdAndAtivo(Long id, boolean ativo);

    Page<Denuncia> findAllByAtivo(boolean ativo, Pageable pageable);
}
