package br.edu.ifsul.api.service;

import br.edu.ifsul.api.controller.response.DenunciaResponse;
import br.edu.ifsul.api.domain.Denuncia;
import br.edu.ifsul.api.domain.Usuario;
import br.edu.ifsul.api.domain.enums.CosmeticoEnum;
import br.edu.ifsul.api.repository.ConteudoRepository;
import br.edu.ifsul.api.repository.DenunciaRepository;
import br.edu.ifsul.api.security.service.UsuarioAutenticadoService;
import br.edu.ifsul.api.service.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static br.edu.ifsul.api.mapper.DenunciaMapper.toResponse;

@Service
public class ListarDenunciasService {

    @Autowired
    private ConteudoRepository conteudoRepository;

    @Autowired
    private BuscarImagemService buscarImagemService;

    @Autowired
    private BuscarConteudoCategoriaService buscarConteudoCategoriaService;

    @Autowired
    private BuscarConteudoImagemService buscarConteudoImagemService;

    @Autowired
    private DenunciaRepository denunciaRepository;

    @Autowired
    private ValidarUsuarioService validarUsuarioService;

    @Autowired
    private UsuarioAutenticadoService autenticadoService;

    @Autowired
    private BuscarUsuarioCosmeticoService buscarUsuarioCosmeticoService;

    public Page<DenunciaResponse> listar(Pageable pageable) {
        Usuario usuario = autenticadoService.get();
        validarUsuarioService.validarAdmin(usuario.getId());

        return denunciaRepository
                .findAllByAtivo(true, pageable)
                .map((Denuncia denuncia) -> {
                    String imagemPerfil = buscarImagemService.emDataUri(denuncia.getConteudo().getUsuario().getImagemPerfil());
                    String[] categorias = buscarConteudoCategoriaService.emVetorPorConteudoId(denuncia.getConteudo().getId());
                    String[] imagens = buscarConteudoImagemService.emVetorPorConteudoId(denuncia.getConteudo().getId());
                    Long insigniaId = buscarUsuarioCosmeticoService.porTipoAtivo(denuncia.getUsuario().getId(), CosmeticoEnum.INSIGNIA)
                            .getCosmetico().getId();
                    return toResponse(denuncia,imagemPerfil,categorias,imagens, insigniaId);
                });
    }
}
