package br.edu.ifsul.api.repository;

import br.edu.ifsul.api.domain.Cosmetico;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface CosmeticoRepository extends JpaRepository<Cosmetico, Long> {

    Page<Cosmetico> findAll(Pageable pageable);

    @Query(value = "SELECT * FROM cosmetico c "+
            "WHERE c.id NOT IN ("+
            "SELECT c.id FROM usuario_cosmetico uc "+
            "LEFT JOIN cosmetico c  ON uc.cosmetico_id = c.id "+
            "WHERE uc.usuario_id = :usuarioId)" +
            "AND c.tipo LIKE (concat('%',:tipo,'%'))"
            , nativeQuery = true
    )
    Page<Cosmetico> visualizarLoja(@Param("usuarioId") Long usuarioId,
                                   @Param("tipo") String tipo, Pageable pageable);

}
