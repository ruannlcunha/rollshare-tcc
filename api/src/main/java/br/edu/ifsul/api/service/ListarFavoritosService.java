package br.edu.ifsul.api.service;

import br.edu.ifsul.api.controller.response.UsuarioResponse;
import br.edu.ifsul.api.domain.Usuario;
import br.edu.ifsul.api.domain.enums.CosmeticoEnum;
import br.edu.ifsul.api.repository.PerfilFavoritoRepository;
import br.edu.ifsul.api.security.service.UsuarioAutenticadoService;
import br.edu.ifsul.api.service.core.BuscarImagemService;
import br.edu.ifsul.api.service.core.BuscarUsuarioCosmeticoService;
import br.edu.ifsul.api.service.core.BuscarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static br.edu.ifsul.api.mapper.UsuarioMapper.toResponse;

@Service
public class ListarFavoritosService {

    @Autowired
    private PerfilFavoritoRepository perfilFavoritoRepository;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private BuscarImagemService buscarImagemService;

    @Autowired
    private BuscarUsuarioCosmeticoService buscarUsuarioCosmeticoService;

    public Page<UsuarioResponse> listar(Pageable pageable) {
        Usuario usuario = usuarioAutenticadoService.get();
        return perfilFavoritoRepository.findAllByDonoId(usuario.getId(),pageable)
                .map(perfilFavorito -> {
                    Usuario alvo = buscarUsuarioService.porId(perfilFavorito.getAlvo().getId());
                    String imagemPerfil = buscarImagemService.emDataUri(alvo.getImagemPerfil());
                    Long insigniaId = buscarUsuarioCosmeticoService.porTipoAtivo(usuario.getId(), CosmeticoEnum.INSIGNIA)
                            .getCosmetico().getId();
                    return toResponse(alvo, imagemPerfil, insigniaId);
                });
    }
}
