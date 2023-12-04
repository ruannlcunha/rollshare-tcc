import useGlobalUser from "../../../context/global-user.context";
import { useExcluirAvaliacao } from "../../../hook";
import { EditarAvaliacaoModal } from "../"
import "./avaliacao.style.css"
import { useNavigate } from "react-router-dom"
import { useState } from "react";

export function AvaliacaoComponent({id, descricao, nota, usuarioId, usuarioNome,
                                     dataInicio, usuarioImagem, fetch}) {

    const navigate = useNavigate()
    const forSize = [1,2,3,4,5]
    const [ globalUser ] = useGlobalUser();
    const [editarIsOpen, setEditarIsOpen] = useState(false);
    const { excluirAvaliacao } = useExcluirAvaliacao(); 
    
    function formatarData() {
        return `${dataInicio.substring(8,10)}/${dataInicio.substring(5,7)} às ${dataInicio.substring(11,16)}`

    }

    async function handleExcluir() {
        await excluirAvaliacao(id);
        await fetch();
    }

    function handleEditar() {
        setEditarIsOpen(true)
    }

    function renderOpcoes() {
        if(usuarioId===globalUser) {
            return (
                <>
                <button onClick={handleEditar} className="editar-avaliacao-botao"></button>
                <button onClick={handleExcluir} className="remover-avaliacao-botao"></button>
                </>
            )
        }return (
            <>
            <button onClick={()=>setDenunciarIsOpen(true)} className="denunciar-conteudo-botao"></button>
            </>
        )
    }

    function renderNota() {

        return (
            <div className="nota-container">
                        {forSize.map(i=> {
                            if(i<=nota) return <div key={i} onClick={()=>setNota(i)} className="nota-ativa"></div>
                            return (<div key={i} onClick={()=>setNota(i)}></div>)
                        })}
            </div>
        )
    }

    return (
        <div className="avaliacao">
            <header>
                <button className="avaliacao-usuario"
                style={{backgroundImage: `url(${usuarioImagem})`}}
                onClick={()=>{navigate(`/perfil/${usuarioId}`)}}
                ></button>

                <section>
                <h3 onClick={()=>{navigate(`/perfil/${usuarioId}`)}}>
                    {usuarioNome}
                    {renderNota()}
                </h3>
                <h4>Postado em: {formatarData()}</h4>
                </section>
                <section className="avaliacao-opcoes">
                    {renderOpcoes()}
                </section>
            </header>
            <section>
                <p>{descricao}</p>
                
            </section>
            <EditarAvaliacaoModal 
            avaliacaoId={id} 
            isOpen={editarIsOpen} 
            setIsOpen={setEditarIsOpen}
            descricaoAvaliacao={descricao} 
            notaAvaliacao={nota}
            fetch={fetch}
            />
        </div>
    )

}