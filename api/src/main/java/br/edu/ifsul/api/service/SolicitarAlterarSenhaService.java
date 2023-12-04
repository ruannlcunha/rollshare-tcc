package br.edu.ifsul.api.service;

import br.edu.ifsul.api.controller.request.EmailRequest;
import br.edu.ifsul.api.domain.Usuario;
import br.edu.ifsul.api.repository.UsuarioRepository;
import br.edu.ifsul.api.service.core.BuscarUsuarioService;
import br.edu.ifsul.api.service.core.EnviarEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@Service
public class SolicitarAlterarSenhaService {

    @Autowired
    private EnviarEmailService enviarEmailService;

    @Autowired
    private BuscarUsuarioService buscarUsuarioService;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    public void solicitar(EmailRequest emailRequest) {
        Usuario usuario = buscarUsuarioService.porEmail(emailRequest.getEmail());
        String token = getToken(usuario.getId());
        usuario.setTokenSeguranca(token);

        final String ASSUNTO = "Recuperar Senha";
        final String TEXTO = "Você está solicitando uma troca de senha, para continuar " +
                "responda a página com o seguinte código: "+token+ ". Caso você não tenha sido " +
                "redirecionado para a página de alteração de senha, acesse : http://localhost:3000/alterar-senha";


        usuarioRepository.save(usuario);
        enviarEmailService.enviarEmail(emailRequest.getEmail(), ASSUNTO, TEXTO);
    }

    private String getToken(Long id) {
        DateFormat token = new SimpleDateFormat("ddMMyyyyHHmmssmm");
        return token.format(new Date())+id;
    }

}
