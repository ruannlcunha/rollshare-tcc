package br.edu.ifsul.api.service.core;

import br.edu.ifsul.api.domain.Conteudo;
import br.edu.ifsul.api.repository.ConteudoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@Service
public class BuscarConteudoService {

    @Autowired
    private ConteudoRepository conteudoRepository;

    public Conteudo porId(Long id) {
        return conteudoRepository.findByIdAndAtivo(id, true)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Conteúdo não existe"));
    }


}
