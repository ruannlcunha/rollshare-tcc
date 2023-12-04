package br.edu.ifsul.api.service;

import br.edu.ifsul.api.controller.request.AlterarConteudoRequest;
import br.edu.ifsul.api.controller.response.ConteudoResponse;
import br.edu.ifsul.api.domain.Categoria;
import br.edu.ifsul.api.domain.Conteudo;
import br.edu.ifsul.api.domain.ConteudoCategoria;
import br.edu.ifsul.api.domain.enums.CosmeticoEnum;
import br.edu.ifsul.api.repository.ConteudoCategoriaRepository;
import br.edu.ifsul.api.service.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static br.edu.ifsul.api.mapper.ConteudoMapper.toResponse;

@Service
public class AlterarConteudoService {

    @Autowired
    private BuscarConteudoService buscarConteudoService;

    @Autowired
    private BuscarImagemService buscarImagemService;

    @Autowired
    private BuscarConteudoCategoriaService buscarConteudoCategoriaService;

    @Autowired
    private BuscarConteudoImagemService buscarConteudoImagemService;

    @Autowired
    private ConteudoCategoriaRepository conteudoCategoriaRepository;

    @Autowired
    private BuscarCategoriaService buscarCategoriaService;

    @Autowired
    private ValidarUsuarioService validarUsuarioService;

    @Autowired
    private BuscarUsuarioCosmeticoService buscarUsuarioCosmeticoService;

    @Transactional
    public ConteudoResponse alterar(Long conteudoId, AlterarConteudoRequest request) {
        Conteudo conteudo = buscarConteudoService.porId(conteudoId);
        validarUsuarioService.validarDono(conteudo.getUsuario().getId());

        conteudo.setDescricao(request.getDescricao());
        conteudo.setFoiAlterado(true);
        alterarCategorias(conteudo, request.getCategorias());

        Integer insigniaId = buscarUsuarioCosmeticoService.porTipoAtivo(conteudo.getUsuario().getId(), CosmeticoEnum.INSIGNIA)
                .getCosmetico().getId().intValue();

        String imagemPerfil = "";
        if(!conteudo.getUsuario().getImagemPerfil().isBlank()) {
            imagemPerfil = buscarImagemService.emDataUri(conteudo.getUsuario().getImagemPerfil());
        }

        String[] categorias = request.getCategorias();
        String[] imagens = buscarConteudoImagemService.emVetorPorConteudoId(conteudo.getId());
        return toResponse(conteudo,imagemPerfil,categorias,insigniaId,imagens);
    }

    private void alterarCategorias(Conteudo conteudo, String[] request) {
        desativarTodasCategoriasAtuais(conteudo);
        adicionarCategorias(conteudo, request);
    }

    private void desativarTodasCategoriasAtuais(Conteudo conteudo) {
        List<ConteudoCategoria> conteudoCategorias = conteudoCategoriaRepository.findByConteudoId(conteudo.getId())
                .stream().map(conteudoCategoria -> {
                    conteudoCategoria.setAtivo(false);
                    conteudoCategoriaRepository.save(conteudoCategoria);
                    return conteudoCategoria;
                }).collect(Collectors.toList());
    }

    private void adicionarCategorias(Conteudo conteudo, String[] request) {
        for(int i=0;i<request.length;i++) {
            if(buscarConteudoCategoriaService.existePorCategoriaNome(request[i],conteudo.getId())) {
                ConteudoCategoria conteudoCategoria =
                        buscarConteudoCategoriaService.porCategoriaNome(request[i],conteudo.getId());
                conteudoCategoria.setAtivo(true);
                conteudoCategoriaRepository.save(conteudoCategoria);
            } else {
                Categoria categoria = buscarCategoriaService.porNome(request[i]);

                ConteudoCategoria conteudoCategoria = new ConteudoCategoria();
                conteudoCategoria.setAtivo(true);

                conteudo.adicionarCategoria(conteudoCategoria);
                categoria.adicionarConteudo(conteudoCategoria);

                conteudoCategoriaRepository.save(conteudoCategoria);
            }
        }
    }

}
