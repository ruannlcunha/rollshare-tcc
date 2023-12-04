package br.edu.ifsul.api.service;

import br.edu.ifsul.api.controller.response.UsuarioDetalhadoResponse;
import br.edu.ifsul.api.domain.Usuario;
import br.edu.ifsul.api.domain.enums.CosmeticoEnum;
import br.edu.ifsul.api.security.service.UsuarioAutenticadoService;
import br.edu.ifsul.api.service.core.BuscarFavoritoService;
import br.edu.ifsul.api.service.core.BuscarImagemService;
import br.edu.ifsul.api.service.core.BuscarUsuarioCosmeticoService;
import br.edu.ifsul.api.service.core.BuscarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.edu.ifsul.api.mapper.UsuarioDetalhadoMapper.toResponse;

@Service
public class VisualizarPerfilService {

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @Autowired
    private BuscarImagemService buscarImagemService;

    @Autowired
    private BuscarUsuarioCosmeticoService buscarUsuarioCosmeticoService;

    @Autowired
    private BuscarFavoritoService buscarFavoritoService;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    public UsuarioDetalhadoResponse visualizar(Long usuarioId) {

        Usuario usuario = buscarUsuarioService.porId(usuarioId);
        Integer fundoId = buscarUsuarioCosmeticoService.porTipoAtivo(usuario.getId(), CosmeticoEnum.FUNDO)
                .getCosmetico().getId().intValue();
        Integer insigniaId = buscarUsuarioCosmeticoService.porTipoAtivo(usuario.getId(), CosmeticoEnum.INSIGNIA)
                .getCosmetico().getId().intValue();
        String imagemPerfilUri = buscarImagemService.emDataUri(usuario.getImagemPerfil());
        String imagemCapaUri = buscarImagemService.emDataUri(usuario.getImagemCapa());
        boolean foiFavoritado = buscarFavoritoService.existeAtivo(usuarioAutenticadoService.getId(), usuario.getId());

        return toResponse(usuario, imagemPerfilUri, imagemCapaUri, fundoId, insigniaId, foiFavoritado);

    }
}
