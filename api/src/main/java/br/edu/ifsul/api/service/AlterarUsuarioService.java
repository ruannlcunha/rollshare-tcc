package br.edu.ifsul.api.service;

import br.edu.ifsul.api.controller.request.AlterarUsuarioRequest;
import br.edu.ifsul.api.controller.response.UsuarioDetalhadoResponse;
import br.edu.ifsul.api.domain.Usuario;
import br.edu.ifsul.api.domain.enums.CosmeticoEnum;
import br.edu.ifsul.api.repository.UsuarioRepository;
import br.edu.ifsul.api.security.service.UsuarioAutenticadoService;
import br.edu.ifsul.api.service.core.AlterarImagemService;
import br.edu.ifsul.api.service.core.BuscarFavoritoService;
import br.edu.ifsul.api.service.core.BuscarUsuarioCosmeticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.edu.ifsul.api.mapper.UsuarioDetalhadoMapper.toResponse;

@Service
public class AlterarUsuarioService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioAutenticadoService autenticadoService;

    @Autowired
    private AlterarImagemService alterarImagemService;

    @Autowired
    private BuscarUsuarioCosmeticoService buscarUsuarioCosmeticoService;

    @Autowired
    private BuscarFavoritoService buscarFavoritoService;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Transactional
    public UsuarioDetalhadoResponse alterar(AlterarUsuarioRequest request) {
        Usuario usuario = autenticadoService.get();

        usuario.setApelido(request.getApelido());
        usuario.setNomeDeUsuario(request.getNomeDeUsuario().toLowerCase());
        usuario.setEmail(request.getEmail());
        Integer fundoId = buscarUsuarioCosmeticoService.porTipoAtivo(usuario.getId(), CosmeticoEnum.FUNDO)
                .getCosmetico().getId().intValue();
        Integer insigniaId = buscarUsuarioCosmeticoService.porTipoAtivo(usuario.getId(), CosmeticoEnum.INSIGNIA)
                .getCosmetico().getId().intValue();

        String imagemPerfilUri = alterarImagemService.alterarPerfil(usuario, request.getImagemDePerfil());
        String imagemCapaUri = alterarImagemService.alterarCapa(usuario, request.getImagemDeCapa());

        boolean foiFavoritado = buscarFavoritoService.existeAtivo(usuarioAutenticadoService.getId(), usuario.getId());

        usuarioRepository.save(usuario);

        return toResponse(usuario, imagemPerfilUri, imagemCapaUri, fundoId, insigniaId, foiFavoritado);
    }
}
