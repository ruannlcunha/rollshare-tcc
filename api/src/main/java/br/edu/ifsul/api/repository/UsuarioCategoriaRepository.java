package br.edu.ifsul.api.repository;

import br.edu.ifsul.api.domain.UsuarioCategoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioCategoriaRepository extends JpaRepository<UsuarioCategoria, Long> {

    List<UsuarioCategoria> findByUsuarioIdAndAtivo(Long id, boolean ativo);

    Optional<UsuarioCategoria> findByCategoriaNomeAndUsuarioId(String nome, Long id);

    boolean existsByCategoriaNomeAndUsuarioId(String nome, Long id);

}
