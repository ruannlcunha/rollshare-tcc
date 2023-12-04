package br.edu.ifsul.api.repository;

import br.edu.ifsul.api.domain.Categoria;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoriaRepository extends JpaRepository<Categoria, Long> {

    Optional<Categoria> findByNome(String nome);


}
