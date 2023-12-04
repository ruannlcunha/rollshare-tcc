package br.edu.ifsul.api.service;

import br.edu.ifsul.api.controller.response.AvaliacaoResponse;
import br.edu.ifsul.api.domain.Avaliacao;
import br.edu.ifsul.api.repository.AvaliacaoRepository;
import br.edu.ifsul.api.service.core.BuscarImagemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import static br.edu.ifsul.api.mapper.AvaliacaoMapper.toResponse;

@Service
public class ListarAvaliacoesService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private BuscarImagemService buscarImagemService;


    public Page<AvaliacaoResponse> listar(Long conteudoId, Pageable pageable) {

        return avaliacaoRepository.findByConteudoIdAndAtivo(conteudoId, true, pageable)
                .map((Avaliacao avaliacao) -> {
                    String imagemPerfil = buscarImagemService.emDataUri(avaliacao.getUsuario().getImagemPerfil());
                    return toResponse(avaliacao,imagemPerfil);
                });
    }
}
