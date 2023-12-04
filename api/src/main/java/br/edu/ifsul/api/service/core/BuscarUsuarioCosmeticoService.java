package br.edu.ifsul.api.service.core;

import br.edu.ifsul.api.domain.UsuarioCosmetico;
import br.edu.ifsul.api.domain.enums.CosmeticoEnum;
import br.edu.ifsul.api.repository.UsuarioCosmeticoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;


@Service
public class BuscarUsuarioCosmeticoService {

    @Autowired
    private UsuarioCosmeticoRepository usuarioCosmeticoRepository;

    public UsuarioCosmetico porCosmetico(Long usuarioId, Long cosmeticoId) {
        return usuarioCosmeticoRepository.findByUsuarioIdAndCosmeticoId(usuarioId, cosmeticoId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Este cosmético não foi comprado"));
    }

    public UsuarioCosmetico porTipoAtivo(Long usuarioId, CosmeticoEnum tipo) {
        return usuarioCosmeticoRepository.findByUsuarioIdAndCosmeticoTipoAndEmUso(usuarioId, tipo, true)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Este cosmético não está ativo."));
    }

}
