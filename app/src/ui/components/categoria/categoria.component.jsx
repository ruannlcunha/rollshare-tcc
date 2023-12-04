import "./categoria.style.css"

export function CategoriaComponent({nome, onClick, ativa, efeito}) {
    
    function renderCategoria() {
        if(ativa) {
            return (
                <button className="categoria-ativa" onClick={()=>onClick()}>
                    {nome}
                </button>
            )
        }
        if(!efeito) {
            return (
                <button className="categoria-sem-efeito" onClick={()=>onClick()}>
                    {nome}
                </button>
            )
        }

        return (
            <button className="categoria" onClick={()=>onClick()}>
                {nome}
            </button>
        )
    }

    return (
        <>
        {renderCategoria()}
        </>
    )

}