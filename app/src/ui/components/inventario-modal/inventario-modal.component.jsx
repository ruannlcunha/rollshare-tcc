import "./inventario-modal.style.css"
import { ModalComponent, CosmeticoComponent, ButtonPrimaryComponent } from ".."
import { useAtivarCosmetico } from "../../../hook";
 
export function InventarioModal({title, isOpen, setIsOpen, cosmeticos, fetch}) {
    const { ativarCosmetico } = useAtivarCosmetico(); 

    async function handleAtivar(id) {
        const cosmetico = await ativarCosmetico(id);
        fetch(cosmetico.id)
        setIsOpen(false)
    }

    function renderAtivarButton(emUso) {
        if(!emUso) return <ButtonPrimaryComponent text={"Ativar"} onClick={()=>handleAtivar(cos.id)}/>
        return <div>Em Uso</div>
    }

    return (
        <ModalComponent isOpen={isOpen} setIsOpen={setIsOpen}>
            <div className="inventario-modal">
                <h1>{title}</h1>
                <section>
                {cosmeticos.content?
                    cosmeticos.content.map(cos=> {
                        return (
                            <CosmeticoComponent id={cos.id} nome={cos.nome}>
                                {renderAtivarButton(cos.emUso)}
                            </CosmeticoComponent>
                            )
                        })
                :null}
                </section>
            </div>
        </ModalComponent>
    )

}