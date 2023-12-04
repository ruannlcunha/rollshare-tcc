package br.edu.ifsul.api.controller;

import br.edu.ifsul.api.controller.response.FavoritoResponse;
import br.edu.ifsul.api.controller.response.UsuarioResponse;
import br.edu.ifsul.api.service.FavoritarPerfilService;
import br.edu.ifsul.api.service.ListarFavoritosService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/favoritos")
public class FavoritoController {

    @Autowired
    private FavoritarPerfilService favoritarPerfilService;

    @Autowired
    private ListarFavoritosService listarFavoritosService;

    @PostMapping("/{favoritoId}")
    public FavoritoResponse favoritarPerfil(@PathVariable Long favoritoId) {
        return favoritarPerfilService.favoritar(favoritoId);
    }

    @GetMapping
    public Page<UsuarioResponse> listar(Pageable pageable) {
        return listarFavoritosService.listar(pageable);
    }

}
