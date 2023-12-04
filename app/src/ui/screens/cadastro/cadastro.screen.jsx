import "./cadastro.style.css"
import { ContainerScreen, InputComponent, SetaVoltarComponent, PergaminhoComponent } from "../../components/";
import { useEffect, useState } from "react";
import { useForm, useCadastro } from "../../../hook";


export function CadastroScreen() {
    const [formActive, setFormActive] = useState(false);
    const {formData, handleChange} = useForm({
        nomeDeUsuario: {value: "", error: ""},
        apelido: {value: "", error: ""},
        email: {value: "", error: ""},
        senha: {value: "", error: ""},
        confirmacaoDeSenha: {value: "", error: ""}
    });
    const { cadastro } = useCadastro();
    
    useEffect(()=> {
        setTimeout(handleFormActive, 1500 )
    },[])

    function handleFormActive() {
        setFormActive(true)
    }

    async function handleSubmit(event) {
        event.preventDefault();
        await cadastro({ 
            nomeDeUsuario: formData.nomeDeUsuario.value,
            apelido: formData.apelido.value,
            email: formData.email.value,
            senha: formData.senha.value,
            confirmacaoDeSenha: formData.confirmacaoDeSenha.value})
    }

    function renderForm() {
        return formActive ? (
            <form onSubmit={handleSubmit}>
                        <InputComponent 
                        label={"Nome de Usuário"}
                        name={"nomeDeUsuario"}
                        value={formData.nomeDeUsuario.value}
                        type={"text"}
                        onChange={handleChange}
                        placeholder={"Digite seu nome de usuário"}
                        />
                        <InputComponent 
                        label={"Apelido"}
                        name={"apelido"}
                        value={formData.apelido.value}
                        type={"text"}
                        onChange={handleChange}
                        placeholder={"Digite seu apelido"}
                        />
                        <InputComponent 
                        label={"Email"}
                        name={"email"}
                        value={formData.email.value}
                        type={"text"}
                        onChange={handleChange}
                        placeholder={"Digite seu email"}
                        />
                        <InputComponent 
                        label={"Senha"}
                        name={"senha"}
                        value={formData.senha.value}
                        type={"password"}
                        onChange={handleChange}
                        placeholder={"Digite sua senha"}
                        />
                        <InputComponent 
                        label={"Confirmação de Senha"}
                        name={"confirmacaoDeSenha"}
                        value={formData.confirmacaoDeSenha.value}
                        type={"password"}
                        onChange={handleChange}
                        placeholder={"Digite novamente sua senha"}
                        />
                        <button>Cadastrar</button>
                    </form>
        ) : null
    }

    return (
        <ContainerScreen>
            <SetaVoltarComponent />
            <div className="cadastro">
                <PergaminhoComponent>
                    {renderForm()}
                </PergaminhoComponent>
            </div>
        </ContainerScreen>
    )
}