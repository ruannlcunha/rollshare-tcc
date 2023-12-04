package br.edu.ifsul.api.service;

import br.edu.ifsul.api.controller.response.CosmeticoResponse;
import br.edu.ifsul.api.domain.enums.CosmeticoEnum;
import br.edu.ifsul.api.repository.UsuarioCosmeticoRepository;
import br.edu.ifsul.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static br.edu.ifsul.api.mapper.CosmeticoMapper.toResponse;

@Service
public class ListarCosmeticosService {

    @Autowired
    private UsuarioCosmeticoRepository usuarioCosmeticoRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public Page<CosmeticoResponse> listar(String tipo, Pageable pageable) {
        Long usuarioId = usuarioAutenticadoService.get().getId();

            return usuarioCosmeticoRepository
                    .findAllByUsuarioIdAndCosmeticoTipo(usuarioId, CosmeticoEnum.valueOf(tipo), pageable)
                    .map(usuarioCosmetico -> toResponse(usuarioCosmetico.getCosmetico(), usuarioCosmetico.isEmUso()));

    }


}
