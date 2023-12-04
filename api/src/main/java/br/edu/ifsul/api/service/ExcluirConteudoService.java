package br.edu.ifsul.api.service;

import br.edu.ifsul.api.domain.Conteudo;
import br.edu.ifsul.api.repository.ConteudoRepository;
import br.edu.ifsul.api.service.core.AlterarRollPointsService;
import br.edu.ifsul.api.service.core.BuscarConteudoService;
import br.edu.ifsul.api.service.core.ValidarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.edu.ifsul.api.domain.enums.RollPointsEnum.ROLLPOINTS_CONTEUDO;

@Service
public class ExcluirConteudoService {

    @Autowired
    private BuscarConteudoService buscarConteudoService;

    @Autowired
    private ConteudoRepository conteudoRepository;

    @Autowired
    private AlterarRollPointsService alterarRollPointsService;

    @Autowired
    private ValidarUsuarioService validarUsuarioService;

    @Transactional
    public void excluir(Long conteudoId) {
        Conteudo conteudo = buscarConteudoService.porId(conteudoId);
        validarUsuarioService.validarDono(conteudo.getUsuario().getId());

        conteudo.setAtivo(false);

        alterarRollPointsService.retirar(ROLLPOINTS_CONTEUDO, conteudo.getUsuario());

        conteudoRepository.save(conteudo);
    }

}
