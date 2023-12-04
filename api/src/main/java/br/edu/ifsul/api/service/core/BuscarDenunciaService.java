package br.edu.ifsul.api.service.core;

import br.edu.ifsul.api.domain.Denuncia;
import br.edu.ifsul.api.repository.DenunciaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@Service
public class BuscarDenunciaService {

    @Autowired
    private DenunciaRepository denunciaRepository;

    public Denuncia porId(Long id) {
        return denunciaRepository.findByIdAndAtivo(id, true)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Denúncia não existe"));
    }


}
