import "./loja.style.css"
import { ContainerScreen, HeaderComponent, CosmeticoComponent, ButtonPrimaryComponent
} from "../../components"
import { useLoja, useComprarCosmetico, useVisualizarUsuario } from "../../../hook"
import { useState, useEffect } from "react"
import useGlobalUser from "../../../context/global-user.context"
import rollpointIcon from "../../../assets/img/rollpoint_icon.png"

export function LojaScreen() {
    const [tipoEscolhido, setTipoEscolhido] = useState("Fundo");
    const { cosmeticos, setCosmeticos, loja } = useLoja();
    const { comprarCosmetico } = useComprarCosmetico();
    const { visualizarUsuario, usuario } = useVisualizarUsuario();
    const [ globalUser ] = useGlobalUser();

    useEffect(()=> {
        loja(tipoEscolhido)
    },[tipoEscolhido])

    useEffect(()=> {
        visualizarUsuario(globalUser)
    },[globalUser])

    function renderTipo(texto) {
        if(tipoEscolhido===texto) {
            return (<li onClick={()=>{setTipoEscolhido(texto)}} className="loja-escolhido">{texto}</li>)
        }
        return (<li onClick={()=>{setTipoEscolhido(texto)}}>{texto}</li>)
    }

    async function handleComprar(id) {
        setCosmeticos(await comprarCosmetico(id,cosmeticos))
        await visualizarUsuario(globalUser)
    }

    function renderCosmeticos() {
        if(!cosmeticos.empty) {
            return (
                cosmeticos.content.map(cosmetico => {
                    return (
                        <CosmeticoComponent key={cosmetico.id} id={cosmetico.id} nome={cosmetico.nome}>
                            <div>
                            <img src={rollpointIcon} alt="Simbolo dos RollPoints" />
                            {cosmetico.custo}
                            </div>
                            <ButtonPrimaryComponent
                            text={"Comprar"}
                            onClick={()=>{handleComprar(cosmetico.id)}}
                            />
                        </CosmeticoComponent>
                    )
                })
            )
        }
        return (
            <div className="loja-vazia">Não há {tipoEscolhido}s disponíveis para compra.</div>
        )
    }

    return (
        <ContainerScreen>
            <HeaderComponent />
            <div className="loja">
                <section className="loja-sidebar">
                    <h1>Loja</h1>
                    <ul>
                        {renderTipo("Fundo")}
                        {renderTipo("Insignia")}
                    </ul>
                    <footer>
                        <img src={rollpointIcon} alt="Simbolo dos RollPoints" />
                        {usuario.rollPoints}
                    </footer>
                </section>

                <section className="loja-opcoes">
                    {cosmeticos?
                        cosmeticos.content?
                        renderCosmeticos()
                        :null
                    :null}
                </section>
            </div>
        </ContainerScreen>
    )
}