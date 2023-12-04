package br.edu.ifsul.api.service.core;

import br.edu.ifsul.api.repository.UsuarioCosmeticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class ValidarCosmeticoCompradoService {

    @Autowired
    private UsuarioCosmeticoRepository usuarioCosmeticoRepository;

    public void validar(Long usuarioId, Long cosmeticoId) {
        if(usuarioCosmeticoRepository.existsByUsuarioIdAndCosmeticoId(usuarioId, cosmeticoId)) {
            throw new ResponseStatusException(BAD_REQUEST, "Você já comprou este cosmético.");
        }
    }

}
