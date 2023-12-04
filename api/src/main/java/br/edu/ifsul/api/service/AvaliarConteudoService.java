package br.edu.ifsul.api.service;

import br.edu.ifsul.api.controller.request.AvaliacaoRequest;
import br.edu.ifsul.api.controller.response.AvaliacaoResponse;
import br.edu.ifsul.api.domain.Avaliacao;
import br.edu.ifsul.api.domain.Conteudo;
import br.edu.ifsul.api.domain.Usuario;
import br.edu.ifsul.api.mapper.AvaliacaoMapper;
import br.edu.ifsul.api.repository.AvaliacaoRepository;
import br.edu.ifsul.api.security.service.UsuarioAutenticadoService;
import br.edu.ifsul.api.service.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.edu.ifsul.api.domain.enums.RollPointsEnum.ROLLPOINTS_AVALIACAO;
import static br.edu.ifsul.api.mapper.AvaliacaoMapper.toEntity;

@Service
public class AvaliarConteudoService {

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @Autowired
    private BuscarConteudoService buscarConteudoService;

    @Autowired
    private UsuarioAutenticadoService autenticadoService;

    @Autowired
    private NowService nowService;

    @Autowired
    private BuscarImagemService buscarImagemService;

    @Autowired
    private AlterarRollPointsService alterarRollPointsService;

    @Transactional
    public AvaliacaoResponse avaliar(Long conteudoId, AvaliacaoRequest request) {
        Usuario usuario = autenticadoService.get();
        Conteudo conteudo = buscarConteudoService.porId(conteudoId);
        String imagemPerfil = buscarImagemService.emDataUri(usuario.getImagemPerfil());

        Avaliacao avaliacao = toEntity(request);
        avaliacao.setFoiAlterado(false);
        avaliacao.setAtivo(true);
        avaliacao.setDataInicio(nowService.getDate());

        usuario.adicionarAvaliacao(avaliacao);
        conteudo.adicionarAvaliacao(avaliacao);

        alterarRollPointsService.conceder(ROLLPOINTS_AVALIACAO, usuario);

        avaliacaoRepository.save(avaliacao);

        return AvaliacaoMapper.toResponse(avaliacao, imagemPerfil);
    }
}
