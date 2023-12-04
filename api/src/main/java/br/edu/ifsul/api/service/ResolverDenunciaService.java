package br.edu.ifsul.api.service;

import br.edu.ifsul.api.controller.request.ResolverDenunciaRequest;
import br.edu.ifsul.api.domain.Conteudo;
import br.edu.ifsul.api.domain.Denuncia;
import br.edu.ifsul.api.domain.Usuario;
import br.edu.ifsul.api.repository.ConteudoRepository;
import br.edu.ifsul.api.repository.DenunciaRepository;
import br.edu.ifsul.api.security.service.UsuarioAutenticadoService;
import br.edu.ifsul.api.service.core.BuscarDenunciaService;
import br.edu.ifsul.api.service.core.ValidarUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ResolverDenunciaService {

    @Autowired
    private DenunciaRepository denunciaRepository;

    @Autowired
    private ConteudoRepository conteudoRepository;

    @Autowired
    private BuscarDenunciaService buscarDenunciaService;

    @Autowired
    private UsuarioAutenticadoService autenticadoService;

    @Autowired
    private ValidarUsuarioService validarUsuarioService;


    @Transactional
    public void resolver(Long denunciaId, ResolverDenunciaRequest request) {
        Usuario usuario = autenticadoService.get();
        validarUsuarioService.validarAdmin(usuario.getId());

        Denuncia denuncia = buscarDenunciaService.porId(denunciaId);
        Conteudo conteudo = denuncia.getConteudo();

        if(request.getRemover()) {
            conteudo.setAtivo(false);
        }

        denuncia.setAtivo(false);

        conteudoRepository.save(conteudo);
        denunciaRepository.save(denuncia);
    }
}
