package br.edu.ifsul.api.service.core;

import br.edu.ifsul.api.controller.request.UsuarioRequest;
import br.edu.ifsul.api.domain.Usuario;
import br.edu.ifsul.api.util.Base64ToMultipartFile;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.server.ResponseStatusException;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.springframework.http.HttpStatus.BAD_REQUEST;

@Service
public class RealizarUploadService {


    public String upload(String dataUri) {

        MultipartFile arquivo = new Base64ToMultipartFile(dataUri);
        try {
            if(!arquivo.isEmpty()) {
                byte[] bytes = arquivo.getBytes();
                String arquivoNome = arquivo.getOriginalFilename();
                Path caminho = Paths.get("data/uploads/"+arquivoNome).toAbsolutePath();
                Files.write(caminho, bytes);
                return arquivoNome;
            }
            else {
                throw new ResponseStatusException(BAD_REQUEST, "Houve um problema no upload do arquivo.");
            }
        }catch (IOException e) {
            e.printStackTrace();
            throw new ResponseStatusException(BAD_REQUEST, "Houve um problema no upload do arquivo.");
        }

    }

}
