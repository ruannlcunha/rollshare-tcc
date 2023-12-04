package br.edu.ifsul.api.controller;

import br.edu.ifsul.api.controller.request.DenunciaRequest;
import br.edu.ifsul.api.controller.request.ResolverDenunciaRequest;
import br.edu.ifsul.api.controller.response.DenunciaResponse;
import br.edu.ifsul.api.service.IncluirDenunciaService;
import br.edu.ifsul.api.service.ListarDenunciasService;
import br.edu.ifsul.api.service.ResolverDenunciaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/denuncias")
public class DenunciaController {

    @Autowired
    private IncluirDenunciaService incluirDenunciaService;

    @Autowired
    private ResolverDenunciaService resolverDenunciaService;

    @Autowired
    private ListarDenunciasService listarDenunciasService;

    @ResponseStatus(CREATED)
    @PostMapping("/{conteudoId}")
    public void incluir(@PathVariable Long conteudoId, @RequestBody DenunciaRequest request) {
        incluirDenunciaService.denunciar(conteudoId, request);
    }

    @ResponseStatus(OK)
    @PutMapping("/resolver/{denunciaId}")
    public void resolver(@PathVariable Long denunciaId, @RequestBody ResolverDenunciaRequest request) {
        resolverDenunciaService.resolver(denunciaId, request);
    }

    @ResponseStatus(OK)
    @GetMapping
    public Page<DenunciaResponse> listar(Pageable pageable) {
        return listarDenunciasService.listar(pageable);
    }

}
