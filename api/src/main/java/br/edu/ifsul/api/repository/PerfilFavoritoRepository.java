package br.edu.ifsul.api.repository;

import br.edu.ifsul.api.domain.PerfilFavorito;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PerfilFavoritoRepository extends JpaRepository<PerfilFavorito, Long> {

    boolean existsByDonoIdAndAlvoId(Long donoId, Long alvoId);

    Optional<PerfilFavorito> findByDonoIdAndAlvoId(Long donoId, Long alvoId);

    Page<PerfilFavorito> findAllByDonoId(Long id, Pageable pageable);

    boolean existsByDonoIdAndAlvoIdAndAtivo(Long donoId, Long alvoId, boolean ativo);
}
