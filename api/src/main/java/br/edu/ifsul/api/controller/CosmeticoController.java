package br.edu.ifsul.api.controller;

import br.edu.ifsul.api.controller.response.CosmeticoResponse;
import br.edu.ifsul.api.service.AtivarCosmeticoService;
import br.edu.ifsul.api.service.ComprarCosmeticoService;
import br.edu.ifsul.api.service.ListarCosmeticosService;
import br.edu.ifsul.api.service.VisualizarLojaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cosmeticos")
public class CosmeticoController {
    @Autowired
    private ComprarCosmeticoService comprarCosmeticoService;

    @Autowired
    private ListarCosmeticosService listarCosmeticosService;

    @Autowired
    private VisualizarLojaService visualizarLojaService;

    @Autowired
    private AtivarCosmeticoService ativarCosmeticoService;

    @PostMapping("/{cosmeticoId}")
    public CosmeticoResponse comprar(@PathVariable Long cosmeticoId) {
        return comprarCosmeticoService.comprar(cosmeticoId);
    }

    @GetMapping
    public Page<CosmeticoResponse> listar(@RequestParam(value = "tipo", required = false)
                                              String tipo, Pageable pageable) {
        return listarCosmeticosService.listar(tipo, pageable);
    }

    @GetMapping("/loja")
    public Page<CosmeticoResponse> visualizarLoja(@RequestParam(value = "tipo", required = false)
                                                      String tipo, Pageable pageable) {
        return visualizarLojaService.visualizar(tipo, pageable);
    }

    @PutMapping("/{cosmeticoId}")
    public CosmeticoResponse ativar(@PathVariable Long cosmeticoId) {
        return ativarCosmeticoService.ativar(cosmeticoId);
    }

}
