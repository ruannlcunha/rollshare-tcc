package br.edu.ifsul.api.service;

import br.edu.ifsul.api.controller.request.AvaliacaoRequest;
import br.edu.ifsul.api.controller.response.AvaliacaoResponse;
import br.edu.ifsul.api.domain.Avaliacao;
import br.edu.ifsul.api.repository.AvaliacaoRepository;
import br.edu.ifsul.api.service.core.BuscarAvaliacaoService;
import br.edu.ifsul.api.service.core.BuscarImagemService;
import br.edu.ifsul.api.service.core.ValidarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.edu.ifsul.api.mapper.AvaliacaoMapper.toResponse;

@Service
public class AlterarAvaliacaoService {

    @Autowired
    private ValidarUsuarioService validarUsuarioService;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private BuscarAvaliacaoService buscarAvaliacaoService;

    @Autowired
    private BuscarImagemService buscarImagemService;


    @Transactional
    public AvaliacaoResponse alterar(Long avaliacaoId, AvaliacaoRequest request) {
        Avaliacao avaliacao = buscarAvaliacaoService.porId(avaliacaoId);
        String imagemPerfil = buscarImagemService.emDataUri(avaliacao.getUsuario().getImagemPerfil());

        validarUsuarioService.validarDono(avaliacao.getUsuario().getId());

        avaliacao.setDescricao(request.getDescricao());
        avaliacao.setNota(request.getNota());
        avaliacao.setFoiAlterado(true);

        avaliacaoRepository.save(avaliacao);

        return toResponse(avaliacao,imagemPerfil);
    }

}
