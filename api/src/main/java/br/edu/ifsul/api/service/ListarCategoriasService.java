package br.edu.ifsul.api.service;

import br.edu.ifsul.api.controller.response.CategoriaResponse;
import br.edu.ifsul.api.domain.Usuario;
import br.edu.ifsul.api.mapper.CategoriaMapper;
import br.edu.ifsul.api.repository.UsuarioCategoriaRepository;
import br.edu.ifsul.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ListarCategoriasService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private UsuarioCategoriaRepository usuarioCategoriaRepository;



    public List<CategoriaResponse> listar() {
        Usuario usuario = usuarioAutenticadoService.get();

        return usuarioCategoriaRepository.findByUsuarioIdAndAtivo(usuario.getId(), true)
                .stream()
                .map(CategoriaMapper::toResponse)
                .collect(Collectors.toList());
    }

}
