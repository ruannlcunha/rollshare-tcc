import "./pergaminho.style.css"
import abaPergaminho from "../../../assets/img/aba_pergaminho.png"

export function PergaminhoComponent({children}) {

    return (
        <section className="pergaminho" onClick={()=>{}}>
            <img src={abaPergaminho} alt="Aba Esquerda do pergaminho" />
            {children}
            <img src={abaPergaminho} alt="Aba Direita do pergaminho" />
        </section>
    )
    
}