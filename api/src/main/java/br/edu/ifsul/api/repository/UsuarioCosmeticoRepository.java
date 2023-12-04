package br.edu.ifsul.api.repository;

import br.edu.ifsul.api.domain.UsuarioCosmetico;
import br.edu.ifsul.api.domain.enums.CosmeticoEnum;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioCosmeticoRepository extends JpaRepository<UsuarioCosmetico, Long> {

    Page<UsuarioCosmetico> findAllByUsuarioId(Long usuarioId, Pageable pageable);


    boolean existsByUsuarioIdAndCosmeticoId(Long usuarioId, Long id);

    Optional<UsuarioCosmetico> findByUsuarioIdAndCosmeticoId(Long usuarioId, Long cosmeticoId);

    Optional<UsuarioCosmetico> findByUsuarioIdAndCosmeticoTipoAndEmUso(Long usuarioId, CosmeticoEnum tipo, boolean ativo);

    Page<UsuarioCosmetico> findAllByUsuarioIdAndCosmeticoTipo(Long usuarioId, CosmeticoEnum tipo, Pageable pageable);
}
