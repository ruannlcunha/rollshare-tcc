package br.edu.ifsul.api.service;

import br.edu.ifsul.api.controller.request.ConteudoRequest;
import br.edu.ifsul.api.controller.response.ConteudoResponse;
import br.edu.ifsul.api.domain.*;
import br.edu.ifsul.api.domain.enums.CosmeticoEnum;
import br.edu.ifsul.api.repository.ConteudoCategoriaRepository;
import br.edu.ifsul.api.repository.ConteudoImagemRepository;
import br.edu.ifsul.api.repository.ConteudoRepository;
import br.edu.ifsul.api.security.service.UsuarioAutenticadoService;
import br.edu.ifsul.api.service.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;

import static br.edu.ifsul.api.domain.enums.RollPointsEnum.ROLLPOINTS_CONTEUDO;
import static br.edu.ifsul.api.mapper.ConteudoMapper.toEntity;
import static br.edu.ifsul.api.mapper.ConteudoMapper.toResponse;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class AdicionarConteudoService {

    @Autowired
    ConteudoRepository conteudoRepository;

    @Autowired
    private UsuarioAutenticadoService autenticadoService;

    @Autowired
    private RealizarUploadService realizarUploadService;

    @Autowired
    private BuscarCategoriaService buscarCategoriaService;

    @Autowired
    NowService nowService;

    @Autowired
    private ConteudoCategoriaRepository conteudoCategoriaRepository;

    @Autowired
    private ConteudoImagemRepository conteudoImagemRepository;

    @Autowired
    private BuscarImagemService buscarImagemService;

    @Autowired
    private AlterarRollPointsService alterarRollPointsService;

    @Autowired
    private BuscarUsuarioCosmeticoService buscarUsuarioCosmeticoService;

    @Transactional
    public ConteudoResponse adicionar(ConteudoRequest request) {
        Usuario usuario = autenticadoService.get();
        validarLimiteDeImagens(request);

        String imagemPerfil = null;
        if(!usuario.getImagemPerfil().isBlank()) {
            imagemPerfil = buscarImagemService.emDataUri(usuario.getImagemPerfil());
        }

        Integer insigniaId = buscarUsuarioCosmeticoService.porTipoAtivo(usuario.getId(), CosmeticoEnum.INSIGNIA)
                .getCosmetico().getId().intValue();

        Conteudo conteudo = criarConteudo(request, usuario);
        adicionarCategorias(request, conteudo);
        adicionarImagens(request,conteudo);

        alterarRollPointsService.conceder(ROLLPOINTS_CONTEUDO, usuario);

        return toResponse(conteudo, imagemPerfil, request.getCategorias(), insigniaId, request.getImagens());
    }

    private Conteudo criarConteudo(ConteudoRequest request, Usuario usuario) {
        Conteudo conteudo = toEntity(request);
        conteudo.setAtivo(true);
        conteudo.setFoiAlterado(false);
        conteudo.setDataInicio(nowService.getDate());
        conteudo.setUsuario(usuario);
        conteudo.setCategorias(new ArrayList<>());
        conteudo.setImagens(new ArrayList<>());

        usuario.adicionarConteudo(conteudo);
        conteudoRepository.save(conteudo);
        return conteudo;
    }

    private void adicionarCategorias(ConteudoRequest request, Conteudo conteudo) {
        for(int i=0;i<request.getCategorias().length;i++) {
            Categoria categoria = buscarCategoriaService.porNome(request.getCategorias()[i]);

            ConteudoCategoria conteudoCategoria = new ConteudoCategoria();
            conteudoCategoria.setAtivo(true);

            conteudo.adicionarCategoria(conteudoCategoria);
            categoria.adicionarConteudo(conteudoCategoria);

            conteudoCategoriaRepository.save(conteudoCategoria);
        }
    }

    private void adicionarImagens(ConteudoRequest request, Conteudo conteudo) {
        for(int i=0;i<request.getImagens().length;i++) {
            ConteudoImagem conteudoImagem = new ConteudoImagem();
            String arquivo = realizarUploadService.upload(request.getImagens()[i]);
            conteudoImagem.setDataUri(arquivo);

            conteudo.adicionarImagem(conteudoImagem);

            conteudoImagemRepository.save(conteudoImagem);
        }
    }

    private void validarLimiteDeImagens(ConteudoRequest request) {
        if(request.getImagens().length>10) {
            throw new ResponseStatusException(BAD_REQUEST, "O máximo de imagens anexadas são 10.");
        }
    }
}
