import "./cosmetico.style.css"
import { ButtonPrimaryComponent } from ".."
import { COSMETICOS } from "../../../constants/cosmeticos.constant"

export function CosmeticoComponent({id, nome, children}) {

    function renderNome() {
        if(nome.length>24) {
            return `${nome.substring(0,22)}[...]`
        }
        return nome
    }

    return (
        <div className="cosmetico">
            <section>
                <div style={{backgroundImage: `url(${COSMETICOS[id]})`}}></div>
            </section>
            <h1>{renderNome()}</h1>
            <footer>
                {children}
            </footer>
        </div>
    )
}