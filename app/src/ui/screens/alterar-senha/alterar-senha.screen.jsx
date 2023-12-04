import "./alterar-senha.style.css"
import { useAlterarSenha, useForm } from "../../../hook"
import { ContainerScreen, InputComponent, ButtonPrimaryComponent, SetaVoltarComponent } from "../../components"

export function AlterarSenhaScreen() {
    const {formData, handleChange} = useForm({
        token: {value: "", error: ""}, senha: {value: "", error: ""}, confirmacaoDeSenha: {value: "", error: ""}});
    const { alterarSenha } = useAlterarSenha();
    
    async function handleSubmit(event) {
        event.preventDefault();
        await alterarSenha(formData.senha.value,formData.confirmacaoDeSenha.value,formData.token.value)

    }

    return (
        <ContainerScreen>
            <div className="alterar-senha">
            <SetaVoltarComponent />
                <section>
                    <h1>Alterar a Senha</h1>
                    <form onSubmit={handleSubmit}>
                        <InputComponent 
                        label={"Digite o token enviado no seu email."}
                        name={"token"}
                        value={formData.token.value}
                        type={"text"}
                        onChange={handleChange}
                        placeholder={"Digite seu token de confirmação"}
                        />
                        <InputComponent 
                        label={"Nova senha"}
                        name={"senha"}
                        value={formData.senha.value}
                        type={"password"}
                        onChange={handleChange}
                        placeholder={"Digite sua nova senha"}
                        />
                        <InputComponent 
                        label={"Confirmação de nova senha"}
                        name={"confirmacaoDeSenha"}
                        value={formData.confirmacaoDeSenha.value}
                        type={"password"}
                        onChange={handleChange}
                        placeholder={"Digite novamente sua nova senha"}
                        />
                        <ButtonPrimaryComponent text={"Alterar"}/>
                    </form>
                </section>
            </div>
        </ContainerScreen>
    )
}