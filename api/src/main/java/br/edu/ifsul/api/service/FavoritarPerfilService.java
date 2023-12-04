package br.edu.ifsul.api.service;

import br.edu.ifsul.api.controller.response.FavoritoResponse;
import br.edu.ifsul.api.domain.PerfilFavorito;
import br.edu.ifsul.api.domain.Usuario;
import br.edu.ifsul.api.repository.PerfilFavoritoRepository;
import br.edu.ifsul.api.security.service.UsuarioAutenticadoService;
import br.edu.ifsul.api.service.core.BuscarFavoritoService;
import br.edu.ifsul.api.service.core.BuscarUsuarioService;
import br.edu.ifsul.api.validator.FavoritoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.edu.ifsul.api.mapper.FavoritoMapper.toResponse;

@Service
public class FavoritarPerfilService {

    @Autowired
    private PerfilFavoritoRepository perfilFavoritoRepository;

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @Autowired
    private BuscarFavoritoService buscarFavoritoService;

    @Autowired
    private FavoritoValidator favoritoValidator;

    @Transactional
    public FavoritoResponse favoritar(Long usuarioFavoritoId) {
        Usuario usuario = usuarioAutenticadoService.get();
        Usuario favorito = buscarUsuarioService.porId(usuarioFavoritoId);
        favoritoValidator.validar(usuario, favorito);

        if(buscarFavoritoService.existe(usuario.getId(), favorito.getId())) {
            PerfilFavorito perfilFavorito = buscarFavoritoService.porId(usuario.getId(), favorito.getId());
            return alteraFavorito(perfilFavorito);
        } else {
            return criarFavorito(usuario, favorito);
        }
    }

    private FavoritoResponse criarFavorito(Usuario usuario, Usuario favorito) {
        PerfilFavorito perfilFavorito = new PerfilFavorito();
        perfilFavorito.setAtivo(true);
        usuario.adicionarFavoritoDono(perfilFavorito);
        favorito.adicionarFavoritoAlvo(perfilFavorito);

        perfilFavoritoRepository.save(perfilFavorito);
        return toResponse(perfilFavorito);
    }

    private FavoritoResponse alteraFavorito(PerfilFavorito perfilFavorito) {
        if (perfilFavorito.isAtivo()) {
            perfilFavorito.setAtivo(false);
            perfilFavoritoRepository.save(perfilFavorito);
            return toResponse(perfilFavorito);
        } else {
            perfilFavorito.setAtivo(true);
            perfilFavoritoRepository.save(perfilFavorito);
            return toResponse(perfilFavorito);
        }
    }

}
