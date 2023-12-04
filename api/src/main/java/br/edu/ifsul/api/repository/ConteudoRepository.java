package br.edu.ifsul.api.repository;

import br.edu.ifsul.api.domain.Conteudo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface ConteudoRepository extends JpaRepository<Conteudo, Long> {
    Page<Conteudo> findAllByUsuarioIdAndAtivo(Long usuarioId, boolean ativo, Pageable pageable);

    Optional<Conteudo> findByIdAndAtivo(Long id, boolean ativo);

    Page<Conteudo> findDistinctByDescricaoContainsAndCategoriasCategoriaNomeContainsAndAtivo(String filtro,
                                                                                        String categoria,
                                                                                        boolean ativo,
                                                                                        Pageable pageable);

    @Query(value = "SELECT DISTINCT c.* from conteudo c " +
            "LEFT JOIN usuario u ON u.id = c.usuario_id " +
            "LEFT JOIN perfil_favorito f ON u.id = f.favorito_id " +
            "LEFT JOIN conteudo_categoria cc ON c.id = cc.conteudo_id " +
            "LEFT JOIN usuario_categoria uc ON cc.categoria_id = uc.categoria_id " +
            "WHERE cc.categoria_id = uc.categoria_id " +
            "AND uc.ativo = true AND c.usuario_id != :usuarioId " +
            "OR c.usuario_id = :usuarioId AND c.ativo = true " +
            "OR c.usuario_id = f.favorito_id AND c.ativo = true " +
            "ORDER BY c.id desc"

            ,countQuery = "SELECT COUNT(*) FROM (SELECT DISTINCT c.* from conteudo c " +
            "LEFT JOIN usuario u ON u.id = c.usuario_id " +
            "LEFT JOIN perfil_favorito f ON u.id = f.favorito_id " +
            "LEFT JOIN conteudo_categoria cc ON c.id = cc.conteudo_id " +
            "LEFT JOIN usuario_categoria uc ON cc.categoria_id = uc.categoria_id " +
            "WHERE cc.categoria_id = uc.categoria_id " +
            "AND uc.ativo = true AND c.usuario_id != :usuarioId) "+
            "OR c.usuario_id = :usuarioId AND c.ativo = true " +
            "OR c.usuario_id = f.favorito_id AND c.ativo = true "
            ,nativeQuery = true)
    Page<Conteudo> feed(@Param("usuarioId") Long usuarioId, Pageable pageable);
}
