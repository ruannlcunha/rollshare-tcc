package br.edu.ifsul.api.mapper;

import br.edu.ifsul.api.controller.response.CosmeticoResponse;
import br.edu.ifsul.api.domain.Cosmetico;

public class CosmeticoMapper {

    public static CosmeticoResponse toResponse(Cosmetico cosmetico, boolean emUso) {
        return CosmeticoResponse.builder()
                .id(cosmetico.getId())
                .nome(cosmetico.getNome())
                .tipo(cosmetico.getTipo().getNome())
                .custo(cosmetico.getCusto())
                .emUso(emUso)
                .build();
    }

}
