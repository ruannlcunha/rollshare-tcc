package br.edu.ifsul.api.service;

import br.edu.ifsul.api.controller.request.CategoriaRequest;
import br.edu.ifsul.api.controller.response.CategoriaResponse;
import br.edu.ifsul.api.domain.Categoria;
import br.edu.ifsul.api.domain.Usuario;
import br.edu.ifsul.api.domain.UsuarioCategoria;
import br.edu.ifsul.api.security.service.UsuarioAutenticadoService;
import br.edu.ifsul.api.service.core.BuscarCategoriaService;
import br.edu.ifsul.api.service.core.BuscarUsuarioCategoriaService;
import br.edu.ifsul.api.service.core.IncluirUsuarioCategoriaService;
import br.edu.ifsul.api.service.core.AlterarUsuarioCategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static br.edu.ifsul.api.mapper.CategoriaMapper.toResponse;

@Service
public class EscolherCategoriaService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private BuscarCategoriaService buscarCategoriaService;

    @Autowired
    private BuscarUsuarioCategoriaService buscarUsuarioCategoriaService;

    @Autowired
    private IncluirUsuarioCategoriaService incluirUsuarioCategoriaService;

    @Autowired
    private AlterarUsuarioCategoriaService alterarUsuarioCategoriaService;

    @Transactional
    public CategoriaResponse escolher(CategoriaRequest request) {
        Usuario usuario = usuarioAutenticadoService.get();
        Categoria categoria = buscarCategoriaService.porNome(request.getNome());

        UsuarioCategoria usuarioCategoria = procuraCategoria(usuario, categoria);

        return toResponse(usuarioCategoria);
    }

    private UsuarioCategoria procuraCategoria(Usuario usuario, Categoria categoria) {
        if(buscarUsuarioCategoriaService.existe(categoria.getNome(), usuario.getId())) {
            UsuarioCategoria usuarioCategoria = buscarUsuarioCategoriaService.buscar(categoria.getNome(), usuario.getId());
            alteraCategoria(usuarioCategoria);
            return usuarioCategoria;
        } else {
            UsuarioCategoria usuarioCategoria = incluirUsuarioCategoriaService.incluirUsuarioCategoria(usuario, categoria);
            usuario.adicionarCategoria(usuarioCategoria);
            categoria.adicionarUsuario(usuarioCategoria);
            return usuarioCategoria;
        }
    }

    private void alteraCategoria(UsuarioCategoria usuarioCategoria) {
        if(usuarioCategoria.isAtivo()) {
            alterarUsuarioCategoriaService.remover(usuarioCategoria);
        } else {
            alterarUsuarioCategoriaService.ativar(usuarioCategoria);
        }
    }

}
