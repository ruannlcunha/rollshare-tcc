import "./categorias.style.css"
import { useEffect } from "react"
import { useListarCategorias, useEscolherCategorias } from "../../../hook"
import { CATEGORIAS } from "../../../constants";

export function CategoriasComponent({fetch}) {
    const { categorias, setCategorias, listarCategorias } = useListarCategorias();
    const { escolherCategorias } = useEscolherCategorias();

    useEffect(()=> {
        listarCategorias()
    },[])

    async function handleEscolher(nome) {
        await escolherCategorias(setCategorias, nome)
        await listarCategorias()
        await fetch()
    }

    return (
        <div className="categorias-container">
            <header>Categorias</header>
            {categorias?
            CATEGORIAS.map((cat, i)=> {
                if(categorias.find(categoria => categoria.nome===cat&&categoria.ativo===true)) {
                    return <section key={i}><button onClick={()=>handleEscolher(cat)} className={"escolhido"}>{cat}</button></section>
                }
                return <section key={i}><button onClick={()=>handleEscolher(cat)}>{cat}</button></section>
            })
            :null}
        </div>
    )
}