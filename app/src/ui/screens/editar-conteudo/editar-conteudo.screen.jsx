import "./editar.conteudo.style.css"
import { ContainerScreen, HeaderComponent, AnuncioComponent, ConteudoEditavelComponent
} from "../../components"
import { useVisualizarConteudo } from "../../../hook";
import { useEffect } from "react";
import { useParams } from "react-router-dom";


export function EditarConteudoScreen() {
    const { conteudo, visualizarConteudo } = useVisualizarConteudo();
    const { id } = useParams();

    useEffect(()=> {
        visualizarConteudo(id);
    },[])

    function renderConteudo() {
        return (
            <ConteudoEditavelComponent 
                        conteudo={conteudo}
                        />
        )
    }

    return (
        <ContainerScreen>
            <HeaderComponent />
            <div className="editar-conteudo">
                <AnuncioComponent />
                <div className="editar-conteudo-container">
                {conteudo.id?
                    renderConteudo()
                    :null}
                </div>
                <AnuncioComponent />
            </div>
        </ContainerScreen>
    )
}