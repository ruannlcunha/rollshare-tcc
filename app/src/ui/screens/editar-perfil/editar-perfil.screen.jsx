import "./editar-perfil.style.css"
import { ContainerScreen, HeaderComponent, EditarPerfilInfoComponent, InventarioModal
} from "../../components"
import { useParams } from "react-router-dom"
import { useListarCosmeticos, useVisualizarUsuario } from "../../../hook";
import { useEffect, useState } from "react";
import { COSMETICOS } from "../../../constants/cosmeticos.constant"

export function EditarPerfilScreen() {
    const { id } = useParams();
    const { visualizarUsuario, usuario } = useVisualizarUsuario();
    const [fundoIsOpen, setFundoIsOpen] = useState();
    const { cosmeticos, listarCosmeticos } = useListarCosmeticos();
    const [fundoId, setFundoId] = useState(usuario.fundoId)
    
    useEffect(()=> {
        visualizarUsuario(id)
        listarCosmeticos("FUNDO")
    },[id])

    useEffect(()=> {
        setFundoId(usuario.fundoId)
    },[usuario])
    
    return (
        <ContainerScreen>
            <HeaderComponent />
            <div className="editar-perfil" style={{backgroundImage: `url(${COSMETICOS[fundoId]})`}}>
                <EditarPerfilInfoComponent usuario={usuario} fetch={visualizarUsuario}/>
                <section className="editar-perfil-fundo">
                    <button onClick={()=>setFundoIsOpen(true)}></button>
                </section>
            </div>
            <InventarioModal 
            title={"Fundos"} 
            cosmeticos={cosmeticos} 
            isOpen={fundoIsOpen} 
            setIsOpen={setFundoIsOpen}
            fetch={setFundoId}
            />
        </ContainerScreen>
    )

}