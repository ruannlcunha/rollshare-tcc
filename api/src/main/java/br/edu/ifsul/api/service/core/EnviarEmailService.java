package br.edu.ifsul.api.service.core;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EnviarEmailService {

    @Autowired
    private JavaMailSender javaMailSender;

    public void enviarEmail(String email, String assunto, String texto) {
        SimpleMailMessage mensagem = new SimpleMailMessage();
        mensagem.setTo(email);
        mensagem.setSubject(assunto);
        mensagem.setText(texto);

        javaMailSender.send(mensagem);
    }

}
