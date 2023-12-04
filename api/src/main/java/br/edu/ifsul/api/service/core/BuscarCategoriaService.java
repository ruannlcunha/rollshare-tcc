package br.edu.ifsul.api.service.core;

import br.edu.ifsul.api.domain.Categoria;
import br.edu.ifsul.api.repository.CategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@Service
public class BuscarCategoriaService {

    @Autowired
    private CategoriaRepository categoriaRepository;

    public Categoria porNome(String nome) {
        return categoriaRepository.findByNome(nome)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Esta categoria não existe"));
    }


}
