package br.edu.ifsul.api.service;

import br.edu.ifsul.api.controller.response.ConteudoResponse;
import br.edu.ifsul.api.domain.Conteudo;
import br.edu.ifsul.api.domain.enums.CosmeticoEnum;
import br.edu.ifsul.api.service.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import static br.edu.ifsul.api.mapper.ConteudoMapper.toResponse;

@Service
public class VisualizarConteudoService {

    @Autowired
    private BuscarConteudoService buscarConteudoService;

    @Autowired
    private BuscarImagemService buscarImagemService;

    @Autowired
    private BuscarConteudoCategoriaService buscarConteudoCategoriaService;

    @Autowired
    private BuscarConteudoImagemService buscarConteudoImagemService;

    @Autowired
    private BuscarUsuarioCosmeticoService buscarUsuarioCosmeticoService;

    public ConteudoResponse visualizar(Long conteudoId) {
        Conteudo conteudo = buscarConteudoService.porId(conteudoId);

        Integer insigniaId = buscarUsuarioCosmeticoService.porTipoAtivo(conteudo.getUsuario().getId(), CosmeticoEnum.INSIGNIA)
                .getCosmetico().getId().intValue();
        String imagemPerfil = buscarImagemService.emDataUri(conteudo.getUsuario().getImagemPerfil());
        String[] categorias = buscarConteudoCategoriaService.emVetorPorConteudoId(conteudo.getId());
        String[] imagens = buscarConteudoImagemService.emVetorPorConteudoId(conteudo.getId());
        return toResponse(conteudo,imagemPerfil,categorias, insigniaId, imagens);
    }
}
