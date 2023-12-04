package br.edu.ifsul.api.service;

import br.edu.ifsul.api.domain.Avaliacao;
import br.edu.ifsul.api.repository.AvaliacaoRepository;
import br.edu.ifsul.api.service.core.AlterarRollPointsService;
import br.edu.ifsul.api.service.core.BuscarAvaliacaoService;
import br.edu.ifsul.api.service.core.ValidarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.edu.ifsul.api.domain.enums.RollPointsEnum.ROLLPOINTS_AVALIACAO;

@Service
public class ExcluirAvaliacaoService {

    @Autowired
    private ValidarUsuarioService validarUsuarioService;

    @Autowired
    private AvaliacaoRepository avaliacaoRepository;

    @Autowired
    private BuscarAvaliacaoService buscarAvaliacaoService;

    @Autowired
    private AlterarRollPointsService alterarRollPointsService;

    @Transactional
    public void excluir(Long avaliacaoId) {
        Avaliacao avaliacao = buscarAvaliacaoService.porId(avaliacaoId);

        validarUsuarioService.validarDono(avaliacao.getUsuario().getId());

        avaliacao.setAtivo(false);

        alterarRollPointsService.retirar(ROLLPOINTS_AVALIACAO, avaliacao.getUsuario());

        avaliacaoRepository.save(avaliacao);
    }

}
