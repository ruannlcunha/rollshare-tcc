package br.edu.ifsul.api.security.service;

import br.edu.ifsul.api.controller.response.UsuarioMinimoResponse;
import br.edu.ifsul.api.domain.Usuario;
import br.edu.ifsul.api.repository.UsuarioRepository;
import br.edu.ifsul.api.security.config.UsuarioSecurity;
import br.edu.ifsul.api.security.domain.Funcao;
import br.edu.ifsul.api.security.repository.PermissaoRepository;
import br.edu.ifsul.api.validator.EmailValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import static br.edu.ifsul.api.mapper.UsuarioMapper.toResponseMinimo;
import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
public class UsuarioAutenticadoService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private EmailValidator emailValidator;

    @Autowired
    private PermissaoRepository permissaoRepository;

    public Long getId() {

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication.getPrincipal() instanceof UsuarioSecurity) {
            return ((UsuarioSecurity) authentication.getPrincipal()).getId();
        }

        return null;
    }

    public Usuario get() {
        Long id = getId();

        if (isNull(id)) {
            return null;
        }

        return usuarioRepository.findById(getId()).orElse(null);
    }

    public UsuarioMinimoResponse getResponse() {
        Usuario entity = get();
        emailValidator.validar(entity);
        boolean isAdmin = permissaoRepository.existsByUsuarioIdAndFuncao(getId(), Funcao.ADMIN);

        return nonNull(entity) ? toResponseMinimo(entity, isAdmin) : new UsuarioMinimoResponse();
    }
}
