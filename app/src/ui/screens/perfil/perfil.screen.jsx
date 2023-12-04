import "./perfil.style.css"
import { ContainerScreen, HeaderComponent, PerfilInfoComponent, AnuncioComponent, SecaoConteudosComponent
} from "../../components"
import { useParams } from "react-router-dom"
import { useListarConteudos, useVisualizarUsuario } from "../../../hook";
import { useEffect, useState } from "react";
import { COSMETICOS } from "../../../constants/cosmeticos.constant"

export function PerfilScreen() {
    const { id } = useParams();
    const { conteudos, listarConteudos } = useListarConteudos();
    const { visualizarUsuario, usuario } = useVisualizarUsuario();
    const [pagina, setPagina] = useState(0);

    useEffect(()=> {
        listarConteudos(id, pagina)
    },[id, pagina])
    
    useEffect(()=> {
        visualizarUsuario(id)
    },[id])
    
    return (
        <ContainerScreen>
            <HeaderComponent />
            <div className="perfil" style={{backgroundImage: `url(${COSMETICOS[usuario.fundoId]})`}}>
                <PerfilInfoComponent usuario={usuario} fetch={visualizarUsuario}/>
                <AnuncioComponent />
                <SecaoConteudosComponent 
                conteudos={conteudos}
                pagina={pagina} 
                setPagina={setPagina}
                fetch={()=>listarConteudos(id, pagina)}
                />
            </div>
        </ContainerScreen>
    )

}