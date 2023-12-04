import "./conteudo.detalhado.style.css"
import { ContainerScreen, HeaderComponent, AnuncioComponent, ConteudoComponent
} from "../../components"
import { useVisualizarConteudo } from "../../../hook";
import { useEffect } from "react";
import { useParams } from "react-router-dom";

export function ConteudoDetalhadoScreen() {
    const { conteudo, visualizarConteudo } = useVisualizarConteudo();
    const { id } = useParams();

    useEffect(()=> {
        visualizarConteudo(id);
    },[])

    function renderConteudo() {
        return (
            <ConteudoComponent 
                        id={conteudo.id} 
                        descricao={conteudo.descricao} 
                        categorias={conteudo.categorias} 
                        dataInicio={conteudo.dataInicio}
                        usuarioNome={conteudo.usuarioNome} 
                        usuarioId={conteudo.usuarioId} 
                        usuarioImagem={conteudo.usuarioImagem} 
                        imagens={conteudo.imagens} 
                        foiAlterado={conteudo.foiAlterado}
                        insigniaId={conteudo.insigniaId}
                        />
        )
    }

    return (
        <ContainerScreen>
            <HeaderComponent />
            <div className="conteudo-detalhado">
                <AnuncioComponent />
                <div className="conteudo-detalhado-container">
                {conteudo.id?
                    renderConteudo()
                    :null}
                </div>
                <AnuncioComponent />
            </div>
        </ContainerScreen>
    )
}