package br.edu.ifsul.api.service;

import br.edu.ifsul.api.controller.response.CosmeticoResponse;
import br.edu.ifsul.api.domain.Cosmetico;
import br.edu.ifsul.api.domain.Usuario;
import br.edu.ifsul.api.domain.UsuarioCosmetico;
import br.edu.ifsul.api.mapper.CosmeticoMapper;
import br.edu.ifsul.api.repository.UsuarioCosmeticoRepository;
import br.edu.ifsul.api.security.service.UsuarioAutenticadoService;
import br.edu.ifsul.api.service.core.AlterarRollPointsService;
import br.edu.ifsul.api.service.core.BuscarCosmeticoService;
import br.edu.ifsul.api.service.core.ValidarCosmeticoCompradoService;
import br.edu.ifsul.api.validator.SaldoValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ComprarCosmeticoService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private BuscarCosmeticoService buscarCosmeticoService;

    @Autowired
    private UsuarioCosmeticoRepository usuarioCosmeticoRepository;

    @Autowired
    private ValidarCosmeticoCompradoService validarCosmeticoCompradoService;

    @Autowired
    private AlterarRollPointsService alterarRollPointsService;

    @Autowired
    private SaldoValidator saldoValidator;

    @Transactional
    public CosmeticoResponse comprar(Long cosmeticoId) {
        Usuario usuario = usuarioAutenticadoService.get();
        Cosmetico cosmetico = buscarCosmeticoService.porId(cosmeticoId);

        saldoValidator.validar(cosmetico.getCusto(), usuario.getRollpoints());
        validarCosmeticoCompradoService.validar(usuario.getId(), cosmetico.getId());

        UsuarioCosmetico usuarioCosmetico = new UsuarioCosmetico();
        usuarioCosmetico.setEmUso(false);

        usuario.adicionarCosmeticos(usuarioCosmetico);
        cosmetico.adicionarUsuario(usuarioCosmetico);

        alterarRollPointsService.retirarComInt(cosmetico.getCusto(), usuario);

        usuarioCosmeticoRepository.save(usuarioCosmetico);
        return CosmeticoMapper.toResponse(cosmetico, usuarioCosmetico.isEmUso());
    }

}
