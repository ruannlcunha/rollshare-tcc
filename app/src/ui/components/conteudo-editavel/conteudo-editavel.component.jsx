import { useNavigate } from "react-router-dom"
import "./conteudo-editavel.style.css"
import { ButtonPrimaryComponent, CategoriaComponent } from ".."
import { COSMETICOS } from "../../../constants/cosmeticos.constant"
import userIcon from "../../../assets/img/user_icon.png"
import { useState } from "react";
import { useEditarConteudo, useForm,  } from "../../../hook"
import { CATEGORIAS } from "../../../constants";

export function ConteudoEditavelComponent({conteudo}) {
    
    const navigate = useNavigate();
    const [categorias, setCategorias] = useState(conteudo.categorias);
    const {formData, handleChange} = useForm({descricao: {value: conteudo.descricao, error: ""}});
    const { editarConteudo } = useEditarConteudo();

    async function handleSubmit(event) {
        event.preventDefault();
        await editarConteudo(conteudo.id, formData.descricao.value, categorias)
    }

    function formatarData() {
        return `${conteudo.dataInicio.substring(8,10)}/${conteudo.dataInicio.substring(5,7)} às ${conteudo.dataInicio.substring(11,16)}`

    }

    function renderFoiAlterado() {
        if(conteudo.foiAlterado) return "(Alterado)"
    }

    function renderImagemPerfil() {
        if(conteudo.usuarioImagem) {
            return (
                <button className="conteudo-usuario"
                style={{backgroundImage: `url(${conteudo.usuarioImagem})`}}
                onClick={()=>{navigate(`/perfil/${conteudo.usuarioId}`)}}
                ></button>
            )
        }
        return (
            <button className="conteudo-usuario"
            style={{backgroundImage: `url(${userIcon})`}}
            onClick={()=>{navigate(`/perfil/${conteudo.usuarioId}`)}}
            ></button>
        )
    }

    function handleEscolher(categoriaNome) {
            if(categorias.find(categoria => categoria===categoriaNome)) {
                setCategorias(old=> old.filter(e => e !== categoriaNome))
            } else {
                setCategorias(old=> [...old, categoriaNome])
            }
    }

    function handleEmptySubmit() {
        event.preventDefault();
    }

    return (
        <>
        <div className="conteudo-editavel-container">
            <header>
                {renderImagemPerfil()}

                <section>
                <h3 onClick={()=>{navigate(`/perfil/${conteudo.usuarioId}`)}}>
                    {conteudo.usuarioNome}
                    <img src={COSMETICOS[conteudo.insigniaId]} alt="Insígnia" />
                </h3>
                <h4>Postado em: {formatarData()} {renderFoiAlterado()}</h4>
                </section>
            </header>

            <section>
                <form onSubmit={handleEmptySubmit}>
                    <h1>Descrição</h1>
                    <textarea
                    name={"descricao"}
                    value={formData.descricao.value}
                    onChange={handleChange}
                    placeholder={"Digite a descrição do seu conteúdo."}
                    />
                    <h1>Categorias</h1>
                    <section>
                        {categorias?
                        CATEGORIAS.map((cat, i)=> {
                            if(categorias.find(categoria => categoria===cat)) {
                                return (
                                <CategoriaComponent key={i} nome={cat} onClick={()=>handleEscolher(cat)} ativa={true}/>
                                )
                            }
                            return <CategoriaComponent key={i} nome={cat} onClick={()=>handleEscolher(cat)}/>
                        })
                        :null}
                        
                    </section>
                    <ButtonPrimaryComponent onClick={handleSubmit} text={"Editar Conteúdo"}/>
                </form>
            </section>
        </div>
        </>
    )

}