import { ContainerScreen, HeaderComponent, SecaoConteudosComponent } from "../../components"
import { useListarDenuncias } from "../../../hook";
import { useEffect, useState } from "react";
import { useResolverDenuncia } from "../../../hook"
import "./denuncias.style.css"

export function DenunciasScreen() {
    const { denuncias, setDenuncias, listarDenuncias } = useListarDenuncias();
    const [pagina, setPagina] = useState(0);
    const { resolverDenuncia } = useResolverDenuncia();

    useEffect(()=> {
        listarDenuncias(pagina);
    },[pagina])

    async function handleResolver(id, remover) {
        await resolverDenuncia(id, remover)
        await listarDenuncias(pagina);
    }

    return (
        <ContainerScreen>
            <HeaderComponent />
            <div className="denuncias">
                <SecaoConteudosComponent 
                conteudos={denuncias} 
                pagina={pagina} 
                setPagina={setPagina} 
                fetch={()=>listarDenuncias(pagina)}
                isDenuncia={true}
                handleResolver={handleResolver}
                >
                </SecaoConteudosComponent>
            </div>
        </ContainerScreen>
    )
}