import "./solicitar-recuperacao.style.css"
import { useSolicitarRecuperacao, useForm } from "../../../hook"
import { ContainerScreen, InputComponent, ButtonPrimaryComponent, SetaVoltarComponent } from "../../components"

export function SolicitarRecuperacaoScreen() {
    const {formData, handleChange} = useForm({
        email: {value: "", error: ""}});
    const { solicitarRecuperacao } = useSolicitarRecuperacao();
    
    async function handleSubmit(event) {
        event.preventDefault();
        await solicitarRecuperacao(formData.email.value)
    }

    return (
        <ContainerScreen>
            <div className="solicitar-recuperacao">
            <SetaVoltarComponent />
                <section>
                    <h1>Esqueceu a Senha</h1>
                    <form onSubmit={handleSubmit}>
                        <InputComponent 
                        label={"Digite o email da sua conta."}
                        name={"email"}
                        value={formData.email.value}
                        type={"text"}
                        onChange={handleChange}
                        placeholder={"Digite seu email"}
                        />
                        <ButtonPrimaryComponent text={"Enviar"}/>
                    </form>
                </section>
            </div>
        </ContainerScreen>
    )
}