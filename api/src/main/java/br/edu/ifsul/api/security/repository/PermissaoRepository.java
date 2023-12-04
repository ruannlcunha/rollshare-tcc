package br.edu.ifsul.api.security.repository;

import br.edu.ifsul.api.security.domain.Funcao;
import br.edu.ifsul.api.security.domain.Permissao;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PermissaoRepository extends JpaRepository<Permissao, Long> {

    boolean existsByUsuarioIdAndFuncao(Long usuarioId, Funcao funcao);

}
