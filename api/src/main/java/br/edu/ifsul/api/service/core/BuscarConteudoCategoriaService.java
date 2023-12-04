package br.edu.ifsul.api.service.core;

import br.edu.ifsul.api.domain.ConteudoCategoria;
import br.edu.ifsul.api.repository.ConteudoCategoriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BuscarConteudoCategoriaService {

    @Autowired
    private ConteudoCategoriaRepository conteudoCategoriaRepository;

    public String[] emVetorPorConteudoId(Long conteudoId) {
        List<ConteudoCategoria> conteudoCategorias = conteudoCategoriaRepository.findByConteudoIdAndAtivo(conteudoId, true);
        String[] categorias = new String[conteudoCategorias.size()];
        for(int i=0;i<conteudoCategorias.size();i++) {
            categorias[i] = conteudoCategorias.get(i).getCategoria().getNome();
        }
        return categorias;
    }

    public ConteudoCategoria porCategoriaNome(String nome, Long conteudoId) {
        return conteudoCategoriaRepository.findByCategoriaNomeAndConteudoId(nome, conteudoId);
    }

    public boolean existePorCategoriaNome(String nome, Long conteudoId) {
        return conteudoCategoriaRepository.existsByCategoriaNomeAndConteudoId(nome, conteudoId);
    }

}
