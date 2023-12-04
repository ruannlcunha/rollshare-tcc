import { useNavigate } from "react-router-dom"
import "./conteudo.style.css"
import { AvaliarModal, AvaliacoesModal, CategoriaComponent, DenunciarModal } from "../"
import { COSMETICOS } from "../../../constants/cosmeticos.constant"
import userIcon from "../../../assets/img/user_icon.png"
import { useState } from "react";
import useGlobalUser from "../../../context/global-user.context";
import { useExcluirConteudo } from "../../../hook";

export function ConteudoComponent({id, descricao, categorias, dataInicio,
                                    usuarioNome, usuarioId, usuarioImagem, 
                                    imagens, foiAlterado, insigniaId, fetch, children}) {
    
    const navigate = useNavigate();
    const [paginaImagem, setPaginaImagem] = useState(0);
    const [avaliacoesIsOpen, setAvaliacoesIsOpen] = useState(false);
    const [avaliarIsOpen, setAvaliarIsOpen] = useState(false);
    const [denunciarIsOpen, setDenunciarIsOpen] = useState(false);
    const [ globalUser ] = useGlobalUser();
    const { excluirConteudo } = useExcluirConteudo();

    async function handleExcluir() {
        await excluirConteudo(id)
        await fetch()
    }
    
    function formatarData() {
        return `${dataInicio.substring(8,10)}/${dataInicio.substring(5,7)} às ${dataInicio.substring(11,16)}`

    }

    function renderFoiAlterado() {
        if(foiAlterado) return "(Alterado)"
    }

    function renderOpcoes() {
        if(usuarioId===globalUser) {
            return (
                <>
                <button onClick={()=>navigate(`/conteudo/${id}/editar`)} className="editar-conteudo-botao"></button>
                <button onClick={handleExcluir} className="remover-conteudo-botao"></button>
                </>
            )
        }return (
            <>
            <button onClick={()=>setDenunciarIsOpen(true)} className="denunciar-conteudo-botao"></button>
            </>
        )
    }

    function renderAvaliarButton() {
        if(usuarioId!==globalUser) {
            return <button onClick={()=>setAvaliarIsOpen(true)}>Avaliar Conteúdo</button>
        }
    }

    function renderImagemPerfil() {
        if(usuarioImagem) {
            return (
                <button className="conteudo-usuario"
                style={{backgroundImage: `url(${usuarioImagem})`}}
                onClick={()=>{navigate(`/perfil/${usuarioId}`)}}
                ></button>
            )
        }
        return (
            <button className="conteudo-usuario"
            style={{backgroundImage: `url(${userIcon})`}}
            onClick={()=>{navigate(`/perfil/${usuarioId}`)}}
            ></button>
        )
    }

    function renderImagens() {
        if(imagens.length>0) {
            return (
                <div className="conteudo-imagens" style={{backgroundImage: `url(${imagens[paginaImagem]})`}}>
                    {renderSetaEsquerda()}
                    {renderSetaDireita()}
                </div>
            )
        }
    }

    function renderSetaEsquerda() {
        if(paginaImagem>0) {
            return (
                <div 
                className="conteudo-seta-esquerda"
                onClick={()=> setPaginaImagem(old=>old-1)}
                ></div>
            )
        }
        return <div></div>
    }

    function renderSetaDireita() {
        if((paginaImagem+1)!==imagens.length && imagens.length>1) {
            return (
                <div 
                className="conteudo-seta-direita"
                onClick={()=> setPaginaImagem(old=>old+1)
                }></div>
            )
        }
        return <div></div>
    }

    const handleDetalhar = event => {
        event.preventDefault();
    
        if (event.target === event.currentTarget) {
            navigate(`/conteudo/${id}`)
        }
    };



    return (
        <>
        <div className="conteudo-container">
            {children}
            <header>
                {renderImagemPerfil()}

                <section>
                <h3 onClick={()=>{navigate(`/perfil/${usuarioId}`)}}>
                    {usuarioNome}
                    <img src={COSMETICOS[insigniaId]} alt="Insígnia" />
                </h3>
                <h4>Postado em: {formatarData()} {renderFoiAlterado()}</h4>
                </section>
                <section className="conteudo-opcoes">
                    {renderOpcoes()}
                </section>
            </header>

            <section onClick={handleDetalhar}>
                <section className="conteudo-categorias">
                    {categorias?
                    categorias.map((cat,i)=> {
                        return <CategoriaComponent key={i} nome={cat} efeito={false}/>
                    })
                    :null}
                </section>
                <p>{descricao}</p>
                {renderImagens()}
            </section>

            <footer>
                {renderAvaliarButton()}
                <button onClick={()=>setAvaliacoesIsOpen(true)}>Ver Avaliações</button>
            </footer>
        </div>
        <AvaliarModal conteudoId={id} isOpen={avaliarIsOpen} setIsOpen={setAvaliarIsOpen}/>
        <AvaliacoesModal conteudoId={id} isOpen={avaliacoesIsOpen} setIsOpen={setAvaliacoesIsOpen}/>
        <DenunciarModal conteudoId={id} isOpen={denunciarIsOpen} setIsOpen={setDenunciarIsOpen}/>
        </>
    )

}