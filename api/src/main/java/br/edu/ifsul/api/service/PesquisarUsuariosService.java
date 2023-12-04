package br.edu.ifsul.api.service;

import br.edu.ifsul.api.controller.response.UsuarioResponse;
import br.edu.ifsul.api.domain.Usuario;
import br.edu.ifsul.api.domain.enums.CosmeticoEnum;
import br.edu.ifsul.api.repository.UsuarioRepository;
import br.edu.ifsul.api.security.service.UsuarioAutenticadoService;
import br.edu.ifsul.api.service.core.BuscarImagemService;
import br.edu.ifsul.api.service.core.BuscarUsuarioCosmeticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static br.edu.ifsul.api.mapper.UsuarioMapper.toResponse;

@Service
public class PesquisarUsuariosService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private UsuarioAutenticadoService autenticadoService;

    @Autowired
    private BuscarImagemService buscarImagemService;

    @Autowired
    private BuscarUsuarioCosmeticoService buscarUsuarioCosmeticoService;

    public Page<UsuarioResponse> pesquisar(String nome, Pageable pageable) {
        Long usuarioId = autenticadoService.get().getId();

        return usuarioRepository
                .findDistinctByApelidoContainingIgnoreCaseAndIdNot(nome,usuarioId, pageable)
                .map((Usuario usuario) -> {
                    String imagemPerfil = buscarImagemService.emDataUri(usuario.getImagemPerfil());
                    Long insigniaId = buscarUsuarioCosmeticoService.porTipoAtivo(usuario.getId(), CosmeticoEnum.INSIGNIA)
                            .getCosmetico().getId();
                    return toResponse(usuario, imagemPerfil, insigniaId);
                });
    }

}
