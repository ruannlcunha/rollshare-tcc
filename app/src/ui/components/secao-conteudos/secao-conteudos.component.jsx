import "./secao-conteudos.style.css"
import { ConteudoComponent, ButtonPrimaryComponent } from "../../components"

export function SecaoConteudosComponent({ children, conteudos, pagina, setPagina, fetch, isDenuncia, handleResolver }) {
    
    function renderConteudo() {
        if(!conteudos.empty) {
            return (
                conteudos.content.map(conteudo=>{
                    return (
                        <ConteudoComponent 
                        key={conteudo.id}
                        id={conteudo.id} 
                        descricao={conteudo.descricao} 
                        categorias={conteudo.categorias} 
                        dataInicio={conteudo.dataInicio}
                        usuarioNome={conteudo.usuarioNome} 
                        usuarioId={conteudo.usuarioId} 
                        usuarioImagem={conteudo.usuarioImagem} 
                        imagens={conteudo.imagens} 
                        foiAlterado={conteudo.foiAlterado}
                        insigniaId={conteudo.insigniaId}
                        fetch={fetch}
                        >
                        {renderDenuncia(conteudo.denunciaId, conteudo.motivo)}
                        </ConteudoComponent>
                    )
                })
            )
        }
        return (
            <div className="conteudo-vazio">Não há conteúdos no momento.</div>
        )
    }

    function renderDenuncia(id, motivo) {
        if(isDenuncia) {
            return (
                <div className="denuncia">
                    <h1>Denúncia</h1>
                    <p><strong>Motivo:</strong> {motivo}</p>
                    <div>
                        <ButtonPrimaryComponent onClick={()=>handleResolver(id, true)} text={"Remover"}/>
                        <ButtonPrimaryComponent onClick={()=>handleResolver(id, false)} text={"Ignorar"}/>
                    </div>
                </div>
            )
        }
    }

    function renderSetaEsquerda() {
        if(pagina>0) {
            return (
                <div 
                className="conteudo-seta-esquerda"
                onClick={()=> setPagina(old=>old-1)}
                ></div>
            )
        }
        return <div></div>
    }

    function renderSetaDireita() {
        if((pagina+1)!==conteudos.totalPages) {
            return (
                <div 
                className="conteudo-seta-direita"
                onClick={()=> setPagina(old=>old+1)
                }></div>
            )
        }
        return <div></div>
    }

    function renderPaginacao() {
        if(!conteudos.empty&&conteudos.totalPages>1) {
            return (
                <footer className="paginacao">
                    {renderSetaEsquerda()}
                    <h1>{(pagina+1)}</h1>
                    {renderSetaDireita()}
                </footer>
            )
        }
    }

    async function handleExcluir() {
        await excluirConteudo(id);
        
    }

    return (
        <section className="secao-conteudos">
            {children}
            {conteudos.content? renderConteudo() : null}
            {conteudos.content? renderPaginacao() : null}
        </section>
    )

}