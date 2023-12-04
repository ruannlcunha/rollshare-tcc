package br.edu.ifsul.api.service.core;

import br.edu.ifsul.api.domain.Avaliacao;
import br.edu.ifsul.api.repository.AvaliacaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@Service
public class BuscarAvaliacaoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    public Avaliacao porId(Long id) {
        return avaliacaoRepository.findByIdAndAtivo(id, true)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Avaliação não existe"));
    }


}
