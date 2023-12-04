package br.edu.ifsul.api.service.core;

import br.edu.ifsul.api.domain.Cosmetico;
import br.edu.ifsul.api.domain.Usuario;
import br.edu.ifsul.api.domain.UsuarioCosmetico;
import br.edu.ifsul.api.repository.UsuarioCosmeticoRepository;
import br.edu.ifsul.api.security.service.UsuarioAutenticadoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class CosmeticosIniciaisService {

    @Autowired
    private UsuarioAutenticadoService usuarioAutenticadoService;

    @Autowired
    private BuscarCosmeticoService buscarCosmeticoService;

    @Autowired
    private UsuarioCosmeticoRepository usuarioCosmeticoRepository;

    @Transactional
    public void adicionarCosmeticosIniciais(Usuario usuario) {
        Cosmetico fundo = buscarCosmeticoService.porId(Long.valueOf(1));
        Cosmetico insignia = buscarCosmeticoService.porId(Long.valueOf(2));

        UsuarioCosmetico fundoAtivo = new UsuarioCosmetico();
        fundoAtivo.setEmUso(true);
        usuario.adicionarCosmeticos(fundoAtivo);
        fundo.adicionarUsuario(fundoAtivo);

        UsuarioCosmetico insigniaAtiva = new UsuarioCosmetico();
        insigniaAtiva.setEmUso(true);
        usuario.adicionarCosmeticos(insigniaAtiva);
        insignia.adicionarUsuario(insigniaAtiva);

        usuarioCosmeticoRepository.save(fundoAtivo);
        usuarioCosmeticoRepository.save(insigniaAtiva);
    }

}
