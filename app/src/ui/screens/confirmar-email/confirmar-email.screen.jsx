import "./confirmar-email.style.css"
import { useConfirmarEmail, useForm } from "../../../hook"
import { ContainerScreen, InputComponent, ButtonPrimaryComponent, SetaVoltarComponent } from "../../components"

export function ConfirmarEmailScreen() {
    const {formData, handleChange} = useForm({
        token: {value: "", error: ""}});
    const { confirmarEmail } = useConfirmarEmail();
    
    async function handleSubmit(event) {
        event.preventDefault();
        await confirmarEmail(formData.token.value)
    }

    return (
        <ContainerScreen>
            <div className="confirmar-email">
            <SetaVoltarComponent />
                <section>
                    <h1>Confirmar Email</h1>
                    <form onSubmit={handleSubmit}>
                        <InputComponent 
                        label={"Insira o token enviado para o seu email."}
                        name={"token"}
                        value={formData.token.value}
                        type={"text"}
                        onChange={handleChange}
                        placeholder={"Digite o token"}
                        />
                        <ButtonPrimaryComponent text={"Confirmar"}/>
                    </form>
                </section>
            </div>
        </ContainerScreen>
    )
}