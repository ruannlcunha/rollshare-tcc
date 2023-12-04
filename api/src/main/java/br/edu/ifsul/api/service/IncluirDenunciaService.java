package br.edu.ifsul.api.service;

import br.edu.ifsul.api.controller.request.DenunciaRequest;
import br.edu.ifsul.api.domain.Conteudo;
import br.edu.ifsul.api.domain.Denuncia;
import br.edu.ifsul.api.repository.DenunciaRepository;
import br.edu.ifsul.api.service.core.BuscarConteudoService;
import br.edu.ifsul.api.service.core.NowService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.edu.ifsul.api.mapper.DenunciaMapper.toEntity;

@Service
public class IncluirDenunciaService {

    @Autowired
    private DenunciaRepository denunciaRepository;

    @Autowired
    private BuscarConteudoService buscarConteudoService;

    @Autowired
    private NowService nowService;

    @Transactional
    public void denunciar(Long conteudoId, DenunciaRequest request) {
        Conteudo conteudo = buscarConteudoService.porId(conteudoId);

        Denuncia denuncia = toEntity(request);
        denuncia.setUsuario(conteudo.getUsuario());
        denuncia.setAtivo(true);
        denuncia.setDataInicio(nowService.getDate());

        conteudo.adicionarDenuncia(denuncia);

        denunciaRepository.save(denuncia);
    }
}
