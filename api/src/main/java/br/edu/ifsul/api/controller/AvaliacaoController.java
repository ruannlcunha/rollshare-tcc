package br.edu.ifsul.api.controller;

import br.edu.ifsul.api.controller.request.AvaliacaoRequest;
import br.edu.ifsul.api.controller.response.AvaliacaoResponse;
import br.edu.ifsul.api.service.AlterarAvaliacaoService;
import br.edu.ifsul.api.service.AvaliarConteudoService;
import br.edu.ifsul.api.service.ExcluirAvaliacaoService;
import br.edu.ifsul.api.service.ListarAvaliacoesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/avaliacoes")
public class AvaliacaoController {

    @Autowired
    private AvaliarConteudoService avaliarConteudoService;

    @Autowired
    private ListarAvaliacoesService listarAvaliacoesService;

    @Autowired
    private AlterarAvaliacaoService alterarAvaliacaoService;

    @Autowired
    private ExcluirAvaliacaoService excluirAvaliacaoService;

    @ResponseStatus(CREATED)
    @PostMapping("/{conteudoId}")
    public AvaliacaoResponse avaliar(@PathVariable Long conteudoId,
                                     @Valid @RequestBody AvaliacaoRequest request) {
        return avaliarConteudoService.avaliar(conteudoId, request);
    }

    @ResponseStatus(OK)
    @GetMapping("/{conteudoId}")
    public Page<AvaliacaoResponse> listarAvaliacoes(@PathVariable Long conteudoId, Pageable pageable) {
        return listarAvaliacoesService.listar(conteudoId, pageable);
    }

    @ResponseStatus(OK)
    @PutMapping("/{avaliacaoId}")
    public AvaliacaoResponse alterarAvaliacao(@PathVariable Long avaliacaoId,
                                              @Valid @RequestBody AvaliacaoRequest request) {
        return alterarAvaliacaoService.alterar(avaliacaoId, request);
    }

    @ResponseStatus(OK)
    @DeleteMapping("{avaliacaoId}")
    public void excluirAvaliacao(@PathVariable Long avaliacaoId) {
        excluirAvaliacaoService.excluir(avaliacaoId);
    }


}
