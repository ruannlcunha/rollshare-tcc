package br.edu.ifsul.api.controller;

import br.edu.ifsul.api.controller.request.*;
import br.edu.ifsul.api.controller.response.CategoriaResponse;
import br.edu.ifsul.api.controller.response.UsuarioDetalhadoResponse;
import br.edu.ifsul.api.controller.response.UsuarioResponse;
import br.edu.ifsul.api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private CadastrarUsuarioService cadastrarUsuarioService;

    @Autowired
    private VisualizarPerfilService visualizarPerfilService;

    @Autowired
    private PesquisarUsuariosService pesquisarUsuariosService;

    @Autowired
    private AlterarUsuarioService alterarUsuarioService;

    @Autowired
    private AlterarSenhaService alterarSenhaService;

    @Autowired
    private EscolherCategoriaService escolherCategoriaService;

    @Autowired
    private SolicitarAlterarSenhaService solicitarAlterarSenhaService;

    @Autowired
    private ConfirmarEmailService confirmarEmailService;

    @Autowired
    private FavoritarPerfilService favoritarPerfilService;

    @Autowired
    private ListarCategoriasService listarCategoriasService;

    @PostMapping
    public void cadastrar(@Valid @RequestBody UsuarioRequest request) {
        cadastrarUsuarioService.cadastrar(request);
    }

    @PutMapping
    public UsuarioDetalhadoResponse alterar(@Valid @RequestBody AlterarUsuarioRequest request) {
        return alterarUsuarioService.alterar(request);
    }

    @PutMapping("/alterar-senha/publico")
    public void alterarSenha(@Valid @RequestBody SenhaRequest request) {
        alterarSenhaService.alterar(request);
    }

    @GetMapping("/{usuarioId}")
    public UsuarioDetalhadoResponse visualizar(@PathVariable Long usuarioId) {
        return visualizarPerfilService.visualizar(usuarioId);
    }

    @GetMapping("/pesquisar")
    public Page<UsuarioResponse> pesquisar(@RequestParam("nome") String nome, Pageable pageable) {
        return pesquisarUsuariosService.pesquisar(nome, pageable);
    }

    @PostMapping("/categorias")
    public CategoriaResponse escolherCategoria(@Valid @RequestBody CategoriaRequest request) {
        return escolherCategoriaService.escolher(request);
    }

    @GetMapping("/categorias")
    public List<CategoriaResponse> listarCategorias() {
        return listarCategoriasService.listar();
    }

    @PostMapping("/solicitar-alterar-senha/publico")
    public void solicitarAlterarSenha(@Valid @RequestBody EmailRequest request) {
        solicitarAlterarSenhaService.solicitar(request);
    }

    @PostMapping("/confirmar-email/publico")
    public void confirmarEmail(@Valid @RequestBody TokenRequest request) {
        confirmarEmailService.confirmarEmail(request);
    }

}
