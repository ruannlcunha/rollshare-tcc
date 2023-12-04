package br.edu.ifsul.api.service.core;

import br.edu.ifsul.api.domain.Usuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlterarImagemService {

    @Autowired
    private BuscarImagemService buscarImagemService;

    @Autowired
    private RealizarUploadService realizarUploadService;

    public String alterarPerfil(Usuario usuario, String novaImagem) {

        if(!usuario.getImagemPerfil().isBlank()) {
            String imagemAtual = buscarImagemService.emDataUri(usuario.getImagemPerfil());
            if (!imagemAtual.equals(novaImagem)) {
                String arquivoPerfil = realizarUploadService.upload(novaImagem);
                usuario.setImagemPerfil(arquivoPerfil);
                return novaImagem;
            }
            return imagemAtual;
        }
        String arquivoPerfil = realizarUploadService.upload(novaImagem);
        usuario.setImagemPerfil(arquivoPerfil);
        return novaImagem;
    }

    public String alterarCapa(Usuario usuario, String novaImagem) {

        if (!usuario.getImagemCapa().isBlank()) {
            String imagemAtual = buscarImagemService.emDataUri(usuario.getImagemCapa());
            if (!imagemAtual.equals(novaImagem)) {
                String arquivoCapa = realizarUploadService.upload(novaImagem);
                usuario.setImagemCapa(arquivoCapa);
                return novaImagem;
            }
        }
            String arquivoCapa = realizarUploadService.upload(novaImagem);
            usuario.setImagemCapa(arquivoCapa);
            return novaImagem;
    }

}
