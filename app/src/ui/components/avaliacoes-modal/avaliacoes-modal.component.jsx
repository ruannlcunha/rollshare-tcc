import "./avaliacoes-modal.style.css"
import { ModalComponent, PergaminhoComponent, AvaliacaoComponent } from ".."
import { useListarAvaliacoes } from "../../../hook";
import { useState, useEffect } from "react";
 
export function AvaliacoesModal({conteudoId, isOpen, setIsOpen}) {
    const [formActive, setFormActive] = useState(false);
    const [page, setPage] = useState(0);
    const { avaliacoes, listarAvaliacoes } = useListarAvaliacoes();

    useEffect(()=> {
        setFormActive(false)
        setTimeout(handleFormActive, 3200 )
    },[isOpen])

    useEffect(()=> {
        listarAvaliacoes(conteudoId, page)
    },[isOpen, page])

    function handleFormActive() {
        setFormActive(true)
    }

    
    function renderSetaEsquerda() {
        if(page>0) {
            return (
                <div 
                className="conteudo-seta-esquerda"
                onClick={()=> setPage(old=>old-1)}
                ></div>
            )
        }
        return <div></div>
    }

    function renderSetaDireita() {
        if((page+1)!==avaliacoes.totalPages) {
            return (
                <div 
                className="conteudo-seta-direita"
                onClick={()=> setPage(old=>old+1)
                }></div>
            )
        }
        return <div></div>
    }

    function renderPaginacao() {
        if(!avaliacoes.empty&&avaliacoes.totalPages>1) {
            return (
                <footer className="paginacao">
                    {renderSetaEsquerda()}
                    <h1>{(page+1)}</h1>
                    {renderSetaDireita()}
                </footer>
            )
        }
    }

    function renderAvaliacoesVazia() {
        if(formActive&avaliacoes.empty) {
            return <h1 className="avaliacoes-vazia">Não há avaliações deste conteúdo</h1>
        }
    }

    return (
        <ModalComponent isOpen={isOpen} setIsOpen={setIsOpen}>
            <PergaminhoComponent>
                <div className="avaliacoes">
                    {renderAvaliacoesVazia()}
                    {avaliacoes.content?
                        avaliacoes.content.map(aval => {
                            if(formActive) {
                                return (
                                    <AvaliacaoComponent 
                                    key={aval.avaliacaoId}
                                    id={aval.avaliacaoId}
                                    descricao={aval.descricao} 
                                    nota={aval.nota} 
                                    usuarioId={aval.usuarioId} 
                                    usuarioNome={aval.usuarioNome}
                                    dataInicio={aval.dataInicio} 
                                    usuarioImagem={aval.usuarioImagem}
                                    fetch={()=>listarAvaliacoes(conteudoId, page)}
                                    />
                                )
                            }
                        })
                    :null}
                {avaliacoes.content? renderPaginacao() : null}
                </div>
            </PergaminhoComponent>
        </ModalComponent>
    )

}