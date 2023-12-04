package br.edu.ifsul.api.service;

import br.edu.ifsul.api.controller.request.UsuarioRequest;
import br.edu.ifsul.api.domain.Usuario;
import br.edu.ifsul.api.repository.UsuarioRepository;
import br.edu.ifsul.api.security.service.PermissaoPadraoService;
import br.edu.ifsul.api.security.service.SenhaCriptografadaService;
import br.edu.ifsul.api.service.core.CosmeticosIniciaisService;
import br.edu.ifsul.api.service.core.EnviarEmailService;
import br.edu.ifsul.api.service.core.ValidaAtributoUnicoService;
import br.edu.ifsul.api.validator.SenhaValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import static br.edu.ifsul.api.mapper.UsuarioMapper.toEntity;

@Service
public class CadastrarUsuarioService {

    private final Integer ROLLPOINTS_INICIAIS = 0;
    private final String IMAGEM_VAZIA = "";

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private SenhaCriptografadaService senhaCriptografadaService;

    @Autowired
    private PermissaoPadraoService permissaoPadraoService;

    @Autowired
    private ValidaAtributoUnicoService validaAtributoUnicoService;

    @Autowired
    private SenhaValidator senhaValidator;

    @Autowired
    private EnviarEmailService enviarEmailService;

    @Autowired
    private CosmeticosIniciaisService cosmeticosIniciaisService;

    @Transactional
    public void cadastrar(UsuarioRequest request) {
        validaAtributoUnicoService.validarNome(request.getNomeDeUsuario());
        validaAtributoUnicoService.validarEmail(request.getEmail());
        senhaValidator.validar(request.getSenha(), request.getConfirmacaoDeSenha());

        Usuario usuario = toEntity(request);
        usuario.setImagemPerfil(IMAGEM_VAZIA);
        usuario.setImagemCapa(IMAGEM_VAZIA);
        usuario.setRollpoints(ROLLPOINTS_INICIAIS);
        usuario.setSenha(senhaCriptografadaService.get(request.getSenha()));
        usuario.adicionarPermissao(permissaoPadraoService.get());
        usuario.setAtivo(true);
        usuario.setEmailValido(false);

        usuarioRepository.save(usuario);
        cosmeticosIniciaisService.adicionarCosmeticosIniciais(usuario);
        enviarEmailConfirmacao(usuario);
    }

    private String getToken(Long id) {
        DateFormat token = new SimpleDateFormat("ddMMyyyyHHmmssmm");
        return token.format(new Date())+id;
    }

    private void enviarEmailConfirmacao(Usuario usuario) {
        String token = getToken(usuario.getId());
        usuario.setTokenSeguranca(token);

        final String ASSUNTO = "Confirmação de Email";
        final String TEXTO = "Obrigado por se cadastrar no RollShare!!, para continuar " +
                "responda a página com o seguinte código: "+token+ ". Caso você não tenha sido " +
                "redirecionado para a página de confirmação de email, acesse : " +
                "http://localhost:3000/confirmar-email";


        enviarEmailService.enviarEmail(usuario.getEmail(), ASSUNTO, TEXTO);
    }
}
