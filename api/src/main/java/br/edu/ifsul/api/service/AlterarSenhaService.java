package br.edu.ifsul.api.service;

import br.edu.ifsul.api.controller.request.SenhaRequest;
import br.edu.ifsul.api.domain.Usuario;
import br.edu.ifsul.api.repository.UsuarioRepository;
import br.edu.ifsul.api.service.core.BuscarUsuarioService;
import br.edu.ifsul.api.security.service.SenhaCriptografadaService;
import br.edu.ifsul.api.validator.SenhaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AlterarSenhaService {

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SenhaValidator senhaValidator;

    @Autowired
    private SenhaCriptografadaService senhaCriptografadaService;

    @Transactional
    public void alterar(SenhaRequest request) {
        Usuario usuario = buscarUsuarioService.porTokenSeguranca(request.getTokenSeguranca());
        senhaValidator.validar(request.getSenha(), request.getConfirmacaoDeSenha());

        usuario.setSenha(senhaCriptografadaService.get(request.getSenha()));
        usuario.setTokenSeguranca(null);

        usuarioRepository.save(usuario);
    }
}
