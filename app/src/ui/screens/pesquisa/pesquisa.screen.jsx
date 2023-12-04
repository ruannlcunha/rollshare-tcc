import "./pesquisa.style.css"
import { ContainerScreen, HeaderComponent, AnuncioComponent, ConteudoComponent, FavoritoComponent
} from "../../components"
import { useParams } from "react-router-dom"
import { useEffect, useState } from "react";
import { usePesquisarConteudos, usePesquisarUsuarios } from "../../../hook";

export function PesquisaScreen() {
    const { filtro, categorias } = useParams();
    const [tipo, setTipo] = useState("CONTEUDOS");
    const [pagina, setPagina] = useState(0);
    const { usuarios, pesquisarUsuarios } = usePesquisarUsuarios();
    const { conteudos, pesquisarConteudos } = usePesquisarConteudos();

    useEffect(()=> {
        pesquisarConteudos(filtro, categorias, pagina)
        pesquisarUsuarios(filtro, pagina)
    },[pagina, filtro])

    useEffect(()=> {
        setPagina(0)
    },[tipo])

    function renderConteudosLabel() {
        if(tipo==="CONTEUDOS") return <label  onClick={()=>setTipo("CONTEUDOS")} className="pesquisa-tipo">Conteúdos</label>
        return <label onClick={()=>setTipo("CONTEUDOS")} >Conteúdos</label>
    }

    function renderUsuariosLabel() {
        if(tipo==="USUARIOS") return <label onClick={()=>setTipo("USUARIOS")} className="pesquisa-tipo">Usuários</label>
        return <label onClick={()=>setTipo("USUARIOS")} >Usuários</label>
    }
    
    function renderPesquisas() {
        if(tipo==="CONTEUDOS"&&conteudos.content) {
            return <section className="pesquisa-section">{renderConteudos()}</section>
        } else {
            if(tipo==="USUARIOS"&&usuarios.content) return <section className="pesquisa-section">{renderUsuarios()}</section>
        }
    }

    function renderUsuarios() {
        if(!usuarios.empty) {
            return (
                usuarios.content.map(usuario=>{
                    return (
                        <FavoritoComponent usuario={usuario}/>
                    )
                })
            )
        }
        return (
            <div className="conteudo-vazio">Não há usuários com este filtro.</div>
        )
    }

    function renderConteudos() {
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
                        </ConteudoComponent>
                    )
                })
            )
        }
        return (
            <div className="conteudo-vazio">Não há conteúdos com estes filtros.</div>
        )
    }


    return (
        <ContainerScreen>
            <HeaderComponent />
            <div className="pesquisa">
                <header className="pesquisa-header">
                    {renderConteudosLabel()}
                    {renderUsuariosLabel()}
                </header>
                <section>
                <AnuncioComponent />
                    {renderPesquisas()}
                <AnuncioComponent />
                </section>
            </div>
        </ContainerScreen>
    )

}