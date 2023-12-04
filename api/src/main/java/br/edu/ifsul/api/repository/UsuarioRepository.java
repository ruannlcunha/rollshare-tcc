package br.edu.ifsul.api.repository;

import br.edu.ifsul.api.domain.Usuario;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {

    Optional<Usuario> findByEmail(String email);

    boolean existsByEmail(String email);

    boolean existsByNomeDeUsuario(String nome);

    Page<Usuario>
    findDistinctByApelidoContainingIgnoreCaseAndIdNot(String apelido, Long usuarioId, Pageable pageable);

    Optional<Usuario> findByTokenSeguranca(String tokenSeguranca);
}
