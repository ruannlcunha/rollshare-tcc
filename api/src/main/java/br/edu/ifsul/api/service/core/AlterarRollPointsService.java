package br.edu.ifsul.api.service.core;

import br.edu.ifsul.api.domain.Usuario;
import br.edu.ifsul.api.domain.enums.RollPointsEnum;
import br.edu.ifsul.api.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AlterarRollPointsService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    public void conceder(RollPointsEnum rollpoints, Usuario usuario) {
        Integer rollPointsAtuais = usuario.getRollpoints();

        usuario.setRollpoints(rollPointsAtuais+rollpoints.getQuantidade());
        usuarioRepository.save(usuario);
    }

    public void retirar(RollPointsEnum rollpoints, Usuario usuario) {
        Integer rollPointsAtuais = usuario.getRollpoints();

        usuario.setRollpoints(rollPointsAtuais-rollpoints.getQuantidade());
        usuarioRepository.save(usuario);
    }

    public void retirarComInt(Integer rollpoints, Usuario usuario) {
        Integer rollPointsAtuais = usuario.getRollpoints();

        usuario.setRollpoints(rollPointsAtuais-rollpoints);
        usuarioRepository.save(usuario);
    }

}
