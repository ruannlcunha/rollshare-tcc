package br.edu.ifsul.api.service;

import br.edu.ifsul.api.controller.response.ConteudoResponse;
import br.edu.ifsul.api.domain.Conteudo;
import br.edu.ifsul.api.domain.enums.CosmeticoEnum;
import br.edu.ifsul.api.repository.ConteudoRepository;
import br.edu.ifsul.api.service.core.BuscarConteudoCategoriaService;
import br.edu.ifsul.api.service.core.BuscarConteudoImagemService;
import br.edu.ifsul.api.service.core.BuscarImagemService;
import br.edu.ifsul.api.service.core.BuscarUsuarioCosmeticoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static br.edu.ifsul.api.mapper.ConteudoMapper.toResponse;

@Service
public class PesquisarConteudosService {

    @Autowired
    private ConteudoRepository conteudoRepository;

    @Autowired
    private BuscarImagemService buscarImagemService;

    @Autowired
    private BuscarConteudoCategoriaService buscarConteudoCategoriaService;

    @Autowired
    private BuscarConteudoImagemService buscarConteudoImagemService;

    @Autowired
    private BuscarUsuarioCosmeticoService buscarUsuarioCosmeticoService;

    public Page<ConteudoResponse> pesquisar(String filtro, String categoria, Pageable pageable) {

        return conteudoRepository
                .findDistinctByDescricaoContainsAndCategoriasCategoriaNomeContainsAndAtivo(
                        filtro, categoria, true, pageable)
                .map((Conteudo conteudo) -> {
                    String imagemPerfil = buscarImagemService.emDataUri(conteudo.getUsuario().getImagemPerfil());
                    String[] categorias = buscarConteudoCategoriaService.emVetorPorConteudoId(conteudo.getId());
                    String[] imagens = buscarConteudoImagemService.emVetorPorConteudoId(conteudo.getId());
                    Integer insigniaId = buscarUsuarioCosmeticoService.porTipoAtivo(conteudo.getUsuario().getId(), CosmeticoEnum.INSIGNIA)
                            .getCosmetico().getId().intValue();
                    return toResponse(conteudo,imagemPerfil,categorias,insigniaId,imagens);
                });
    }

}
