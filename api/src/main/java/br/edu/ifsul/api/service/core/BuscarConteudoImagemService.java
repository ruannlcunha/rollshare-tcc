package br.edu.ifsul.api.service.core;

import br.edu.ifsul.api.domain.ConteudoImagem;
import br.edu.ifsul.api.repository.ConteudoImagemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarConteudoImagemService {

    @Autowired
    private ConteudoImagemRepository conteudoImagemRepository;

    @Autowired
    private BuscarImagemService buscarImagemService;


    public String[] emVetorPorConteudoId(Long conteudoId) {
        List<ConteudoImagem> conteudoImagems = conteudoImagemRepository.findByConteudoId(conteudoId);
        String[] imagens = new String[conteudoImagems.size()];
        for(int i=0;i<conteudoImagems.size();i++) {
            imagens[i] = buscarImagemService.emDataUri(conteudoImagems.get(i).getDataUri());
        }
        return imagens;
    }

}
