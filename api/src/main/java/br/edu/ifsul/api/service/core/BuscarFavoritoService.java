package br.edu.ifsul.api.service.core;

import br.edu.ifsul.api.domain.PerfilFavorito;
import br.edu.ifsul.api.repository.PerfilFavoritoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class BuscarFavoritoService {

    @Autowired
    private PerfilFavoritoRepository perfilFavoritoRepository;

    public boolean existe(Long donoId, Long alvoId) {
        return perfilFavoritoRepository.existsByDonoIdAndAlvoId(donoId, alvoId);
    }

    public boolean existeAtivo(Long donoId, Long alvoId) {
        return perfilFavoritoRepository.existsByDonoIdAndAlvoIdAndAtivo(donoId, alvoId, true);
    }

    public PerfilFavorito porId(Long donoId, Long alvoId) {
        return perfilFavoritoRepository.findByDonoIdAndAlvoId(donoId, alvoId)
                .orElseThrow(() -> new ResponseStatusException(NOT_FOUND, "Favorito não existe"));
    }

}
