package br.edu.ifsul.api.service;

import br.edu.ifsul.api.controller.response.CosmeticoResponse;
import br.edu.ifsul.api.domain.Usuario;
import br.edu.ifsul.api.domain.UsuarioCosmetico;
import br.edu.ifsul.api.repository.UsuarioCosmeticoRepository;
import br.edu.ifsul.api.security.service.UsuarioAutenticadoService;
import br.edu.ifsul.api.service.core.BuscarUsuarioCosmeticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.edu.ifsul.api.mapper.CosmeticoMapper.toResponse;

@Service
public class AtivarCosmeticoService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private UsuarioCosmeticoRepository usuarioCosmeticoRepository;

    @Autowired
    private BuscarUsuarioCosmeticoService buscarUsuarioCosmeticoService;

    @Transactional
    public CosmeticoResponse ativar(Long cosmeticoId) {
        Usuario usuario = usuarioAutenticadoService.get();
        UsuarioCosmetico cosmetico = buscarUsuarioCosmeticoService.porCosmetico(usuario.getId(),cosmeticoId);
        UsuarioCosmetico cosmeticoAntigo = buscarUsuarioCosmeticoService.porTipoAtivo(usuario.getId(),
                cosmetico.getCosmetico().getTipo());

        cosmeticoAntigo.setEmUso(false);
        cosmetico.setEmUso(true);
        usuarioCosmeticoRepository.save(cosmetico);

        return toResponse(cosmetico.getCosmetico(), true);
    }

}
