import "./editar-perfil-info.style.css"
import userIcon from "../../../assets/img/user_icon.png"
import userCapa from "../../../assets/img/capa_padrao.png"
import { COSMETICOS } from "../../../constants/cosmeticos.constant"
import { ButtonPrimaryComponent, InputComponent, InventarioModal } from "../"
import { useAlterarPerfil, useConvertImage, useForm, useListarCosmeticos } from "../../../hook"
import { useEffect, useState } from "react"

export function EditarPerfilInfoComponent({ usuario }) {
    const { alterarPerfil } = useAlterarPerfil();
    const [insigniaIsOpen, setInsigniaIsOpen] = useState();
    const [insigniaId, setInsigniaId] = useState(usuario.insigniaId)
    const { convertImageTo64 } = useConvertImage();
    const { cosmeticos, listarCosmeticos } = useListarCosmeticos();
    const {formData, setFormData, handleChange} = useForm({
        nomeDeUsuario: {value: usuario.nomeDeUsuario, error: ""},
        apelido: {value: usuario.apelido, error: ""},
        email: {value: usuario.email, error: ""},
        imagemCapa: {value: usuario.imagemCapa, error: ""},
        imagemPerfil: {value: usuario.imagemPerfil, error: ""}
    });

    useEffect(()=> {
        setFormData({
            nomeDeUsuario: {value: usuario.nomeDeUsuario, error: ""},
            apelido: {value: usuario.apelido, error: ""},
            email: {value: usuario.email, error: ""},
            imagemCapa: {value: usuario.imagemCapa, error: ""},
            imagemPerfil: {value: usuario.imagemPerfil, error: ""}
        })
        listarCosmeticos("INSIGNIA")
        setInsigniaId(usuario.insigniaId)
    },[usuario])

    function renderImagemPerfil() {
        if(formData.imagemPerfil.value) {
            return (
            <div className="editar-perfil-foto" style={{backgroundImage: `url(${formData.imagemPerfil.value})`}}>
                <label htmlFor="imagemPerfil"></label>
                <input type="file" name="imagemPerfil" id="imagemPerfil" 
                onChange={()=>handleChangeImagem("imagemPerfil")}/>
            </div>
            )
        }
        return (
            <div className="editar-perfil-foto" style={{backgroundImage: `url(${userIcon})`}}>
                <label htmlFor="imagemPerfil"></label>
                <input type="file" name="imagemPerfil" id="imagemPerfil" 
                onChange={()=>handleChangeImagem("imagemPerfil")}/>
            </div>
            )
    }

    function renderImagemCapa() {
        if(formData.imagemCapa.value) {
            return (
            <div className="editar-perfil-capa" style={{backgroundImage: `url(${formData.imagemCapa.value})`}}>
                <label htmlFor="imagemCapa"></label>
                <input type="file" name="imagemCapa" id="imagemCapa" 
                onChange={()=>handleChangeImagem("imagemCapa")}/>
            </div>
            )
        }
        return (
            <div className="editar-perfil-capa" style={{backgroundImage: `url(${userCapa})`}}>
                <label htmlFor="imagemCapa"></label>
                <input type="file" name="imagemCapa" id="imagemCapa" 
                onChange={()=>handleChangeImagem("imagemCapa")}/>
            </div>
            )
    }

    async function handleSubmit(event) {
        event.preventDefault();
        await alterarPerfil({ 
            nomeDeUsuario: formData.nomeDeUsuario.value,
            apelido: formData.apelido.value,
            email: formData.email.value,
            imagemDeCapa: formData.imagemCapa.value,
            imagemDePerfil: formData.imagemPerfil.value})
    }

    async function handleChangeImagem(fieldName) {
        const file = document.getElementById(fieldName).files[0];
        const file64 = await convertImageTo64(file);
        setFormData((pastInfo) => ({
            ...pastInfo,
            [fieldName]: {
              value: file64,
              error: ""
            }
          }));
    }

    return (
        <section className="editar-perfil-info">
            {renderImagemCapa()}
            <section className="editar-perfil-usuario-nomes">
                {renderImagemPerfil()}
                <section>
                    <h1>
                        <input 
                        type="text" 
                        name="apelido" 
                        value={formData.apelido.value} 
                        placeholder={"Digite seu apelido"}
                        onChange={handleChange}
                        />
                    </h1>
                    <h3>
                        @
                        <input 
                        type="text" 
                        name="nomeDeUsuario" 
                        value={formData.nomeDeUsuario.value} 
                        placeholder={"Digite seu nome de usuario"}
                        onChange={handleChange}
                        />
                    </h3>
                </section>
                <div 
                className="editar-insignia" 
                style={{backgroundImage:`url(${COSMETICOS[insigniaId]})`}}>
                    <button onClick={()=>setInsigniaIsOpen(true)}></button>
                </div>
            </section>

            <section className="editar-perfil-usuario-infos">
                <InputComponent 
                label={"Email"}
                name={"email"}
                value={formData.email.value}
                type={"text"}
                onChange={handleChange}
                placeholder={"Digite seu email"}
                />
                <ButtonPrimaryComponent text={"Editar"} onClick={handleSubmit}/>
            </section>
            <InventarioModal 
            title={"Insígnias"} 
            cosmeticos={cosmeticos} 
            isOpen={insigniaIsOpen} 
            setIsOpen={setInsigniaIsOpen}
            fetch={setInsigniaId}
            />
        </section>
    )
}