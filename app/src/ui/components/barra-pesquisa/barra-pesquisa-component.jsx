import "./barra-pesquisa.style.css"
import { useForm } from "../../../hook";
import lupaIcon from "../../../assets/img/lupa_icon.png"
import lupaIconHover from "../../../assets/img/lupa_icon_hover.png"
import { CategoriaComponent, InputComponent, ButtonPrimaryComponent } from "../"
import { CATEGORIAS } from "../../../constants";
import { useEffect, useState } from "react";
import { useNavigate } from "react-router-dom";

export function BarraPesquisaComponent() {
    const [categorias, setCategorias] = useState([]);
    const navigate = useNavigate();
    const {formData, setFormData, handleChange} = useForm({
        pesquisa: {value: "", error: ""}});
    
    useEffect(()=> {
        if(!formData.pesquisa.value) {
            setCategorias([])
        }
    },[formData.pesquisa.value])

    async function handleSubmit(event) {
        event.preventDefault();
    }

    function handleEscolher(categoriaNome) {
            if(categorias.find(categoria => categoria===categoriaNome)) {
                setCategorias(old=> old.filter(e => e !== categoriaNome))
            } else {
                setCategorias(old=> [...old, categoriaNome])
            }
    }

    function handlePesquisar() {
        setCategorias([])
        setFormData({pesquisa: {value: "", error: ""}})
        navigate(`/pesquisar/${formData.pesquisa.value}/${formatCategorias()}`)
    }

    function formatCategorias() {
        let categoriaFiltro = ""
        if(categorias.length===0) return categoriaFiltro
        if(categorias.length===1) return categorias[0]

        for(let i=0;i<categorias.length;i++) {
            if((i+1)===categorias.length) {
                categoriaFiltro = categoriaFiltro+categorias[i]
            } else {
                categoriaFiltro = categoriaFiltro+categorias[i]+","
            }
        }

        return categoriaFiltro
    }

    function renderLupa() {
        if(formData.pesquisa.value) return lupaIconHover
        return lupaIcon
    }

    function selectClassName() {
        if(formData.pesquisa.value) return "barra-pesquisa-ativa"
        return "barra-pesquisa"
    }

    function renderPesquisaAtiva() {
        if(formData.pesquisa.value) {
            return (
                <section>
                    <h1>Categorias relacionadas</h1>
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
                    <ButtonPrimaryComponent text={"Pesquisar"} onClick={handlePesquisar}/>
                </section>
            )
        }
    }

    return (
        <div className={selectClassName()}>
        <form onSubmit={handleSubmit}>
                <img src={renderLupa()} alt="Lupa verde" />
                <InputComponent
                name={"pesquisa"}
                value={formData.pesquisa.value}
                type={"text"}
                onChange={handleChange}
                placeholder={"Pesquise um conteúdo ou um usuário."}
                />
        </form>
        {renderPesquisaAtiva()}
        </div>
    )

}