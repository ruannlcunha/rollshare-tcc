package br.edu.ifsul.api.service.core;

import br.edu.ifsul.api.domain.Cosmetico;
import br.edu.ifsul.api.repository.CosmeticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@Service
public class BuscarCosmeticoService {

    @Autowired
    private CosmeticoRepository cosmeticoRepository;

    public Cosmetico porId(Long id) {
        return cosmeticoRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Este cosmético não existe"));
    }


}
