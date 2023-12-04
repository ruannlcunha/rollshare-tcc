package br.edu.ifsul.api.service;

import br.edu.ifsul.api.controller.response.CosmeticoResponse;
import br.edu.ifsul.api.domain.Cosmetico;
import br.edu.ifsul.api.mapper.CosmeticoMapper;
import br.edu.ifsul.api.repository.CosmeticoRepository;
import br.edu.ifsul.api.repository.UsuarioCosmeticoRepository;
import br.edu.ifsul.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class VisualizarLojaService {

    @Autowired
    private CosmeticoRepository cosmeticoRepository;

    @Autowired
    private UsuarioCosmeticoRepository usuarioCosmeticoRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public Page<CosmeticoResponse> visualizar(String tipo, Pageable pageable) {
        Long usuarioId = usuarioAutenticadoService.get().getId();

        return cosmeticoRepository.visualizarLoja(usuarioId, tipo, pageable)
                .map((Cosmetico cosmetico) -> CosmeticoMapper.toResponse(cosmetico, false));

    }
}
