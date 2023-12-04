import { ContainerScreen, HeaderComponent, CategoriasComponent, AnuncioComponent, SecaoConteudosComponent,
    CategoriaComponent
 } from "../../components"
import { useFeed, useForm, useAdicionarConteudo, useConvertImage } from "../../../hook";
import { useEffect, useState } from "react";
import { CATEGORIAS } from "../../../constants";
import "./feed.style.css"

export function FeedScreen() {
    const { conteudos, feed } = useFeed();
    const [pagina, setPagina] = useState(0);
    const [categorias, setCategorias] = useState([]);
    const [imagens, setImagens] = useState([]);
    const { adicionarConteudo } = useAdicionarConteudo();
    const { convertImageTo64 } = useConvertImage();
    const {formData, setFormData, handleChange} = useForm({descricao: {value: "", error: ""}});

    useEffect(()=> {
        feed(pagina);
    },[pagina,categorias])

    async function handleSubmit(event) {
        event.preventDefault();
        const files = document.getElementById("imagensInput").files;
        const newImages = await convertImages(files);
        await adicionarConteudo(
            formData.descricao.value,
            categorias,
            newImages
        )
        await feed(pagina)
        setFormData({descricao: {value: "", error: ""}});
        setCategorias([])
        setImagens([])
    }

    async function convertImages(files) {
        const novasImagens = [];
        for(let i=0;i<files.length;i++) {
            const file64 = await convertImageTo64(files.item(i));
            novasImagens.push(file64)
        }
        setImagens(novasImagens);
        return novasImagens;
    }

    function renderFormData() {
        return (<>
                    <textarea
                    name={"descricao"}
                    value={formData.descricao.value}
                    onChange={handleChange}
                    placeholder={"Digite a descrição do seu conteúdo."}
                    />
                </>
        )
    }

    function handleEscolher(categoriaNome) {
            if(categorias.find(categoria => categoria===categoriaNome)) {
                setCategorias(old=> old.filter(e => e !== categoriaNome))
            } else {
                setCategorias(old=> [...old, categoriaNome])
            }
    }

    function handleSubmitEmpty(event) {
        event.preventDefault();
    }

    function renderAdicionarConteudo() {
        if(formData.descricao.value) {
            return (
                <div className="adicionar-conteudo-ativado">
                <form onSubmit={handleSubmitEmpty}>
                    <h1>Adicione uma descrição</h1>
                    {renderFormData()}
                    <h1>Adicione imagens</h1>
                    <input
                    id={"imagensInput"}
                    name={"imagens"}
                    type={"file"}
                    className="imagens-input"
                    multiple
                    />
                    <h1>Escolha as categorias relacionadas</h1>
                    <section>
                    {categorias?
                        CATEGORIAS.map((cat, i)=> {
                            if(categorias.find(categoria => categoria===cat)) {
                                return (
                                    <CategoriaComponent key={i} nome={cat} onClick={()=>handleEscolher(cat)} ativa={true}/>
                                    )
                                }
                                return <CategoriaComponent key={i} nome={cat} onClick={()=>handleEscolher(cat)} efeito={true}/>
                            })
                            :null}
                    </section>
                    <button className="adicionar-conteudo-botao" onClick={handleSubmit}>Enviar</button>
                </form>
                </div>
            )
        }

        return (
            <div className="adicionar-conteudo">
            <form onSubmit={handleSubmit}>
                <h1>Adicione um conteúdo</h1>
                {renderFormData()}
            </form>
            </div>
        )
    }

    return (
        <ContainerScreen>
            <HeaderComponent />
            <div className="feed">
                <AnuncioComponent />
                <SecaoConteudosComponent 
                conteudos={conteudos} 
                pagina={pagina} 
                setPagina={setPagina} 
                fetch={()=>feed(pagina)}>

                    {renderAdicionarConteudo()}
                    
                </SecaoConteudosComponent>
                <AnuncioComponent />
                <CategoriasComponent fetch={()=>feed(pagina)}/>
            </div>
        </ContainerScreen>
    )
}