package br.edu.ifsul.api.security.controller;

import br.edu.ifsul.api.controller.response.UsuarioMinimoResponse;
import br.edu.ifsul.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UsuarioAutenticadoService service;

    @PostMapping
    public UsuarioMinimoResponse login() {
        return service.getResponse();
    }
}
