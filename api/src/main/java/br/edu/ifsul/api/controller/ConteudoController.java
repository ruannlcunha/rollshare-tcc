package br.edu.ifsul.api.controller;

import br.edu.ifsul.api.controller.request.AlterarConteudoRequest;
import br.edu.ifsul.api.controller.request.ConteudoRequest;
import br.edu.ifsul.api.controller.response.ConteudoResponse;
import br.edu.ifsul.api.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/conteudos")
public class ConteudoController {

    @Autowired
    private AdicionarConteudoService adicionarConteudoService;

    @Autowired
    private VisualizarConteudoService visualizarConteudoService;

    @Autowired
    private AlterarConteudoService alterarConteudoService;

    @Autowired
    private ExcluirConteudoService excluirConteudoService;

    @Autowired
    private ListarConteudosService listarConteudosService;

    @Autowired
    private PesquisarConteudosService pesquisarConteudosService;

    @Autowired
    private VisualizarFeedService visualizarFeedService;

    @ResponseStatus(CREATED)
    @PostMapping
    public ConteudoResponse adicionar(@Valid @RequestBody ConteudoRequest request) {
        return adicionarConteudoService.adicionar(request);
    }

    @ResponseStatus(OK)
    @GetMapping("/{conteudoId}/detalhar")
    public ConteudoResponse visualizar(@PathVariable Long conteudoId) {
        return visualizarConteudoService.visualizar(conteudoId);
    }

    @ResponseStatus(OK)
    @PutMapping("/{conteudoId}")
    public ConteudoResponse alterar(@PathVariable Long conteudoId,
                                    @Valid @RequestBody AlterarConteudoRequest request) {
        return alterarConteudoService.alterar(conteudoId, request);
    }

    @ResponseStatus(OK)
    @DeleteMapping("/{conteudoId}")
    public void excluir(@PathVariable Long conteudoId) {
        excluirConteudoService.excluir(conteudoId);
    }

    @GetMapping("/{usuarioId}")
    public Page<ConteudoResponse> listarConteudos(@PathVariable Long usuarioId, Pageable pageable) {
        return listarConteudosService.listar(usuarioId, pageable);
    }

    @GetMapping("/pesquisar")
    public Page<ConteudoResponse> pesquisar(@RequestParam(value = "filtro", required = false) String filtro,
                                                  @RequestParam(value = "categoria", required = false) String categoria,
                                                  Pageable pageable) {
        return pesquisarConteudosService.pesquisar(filtro, categoria, pageable);
    }

    @GetMapping("/feed")
    public Page<ConteudoResponse> visualizarFeed(Pageable pageable) {
        return visualizarFeedService.visualizar(pageable);
    }

}
