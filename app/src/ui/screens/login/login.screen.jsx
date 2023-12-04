import "./login.style.css"
import logo from "../../../assets/img/rollshare_logo2.png"
import { ContainerScreen, InputComponent, SetaVoltarComponent, ButtonPrimaryComponent } from "../../components/";
import { useLogin, useForm } from "../../../hook";
import { useNavigate } from "react-router-dom";

export function LoginScreen() {
    const navigate = useNavigate();
    const {formData, handleChange} = useForm({
        usuario: {value: "", error: ""},
        senha: {value: "", error: ""}});
    const { login } = useLogin();
    
    async function handleSubmit(event) {
        event.preventDefault();
        await login({username: formData.usuario.value, password: formData.senha.value })
    }

    return (
        <ContainerScreen>
            <div className="login-fundo"> 
            <SetaVoltarComponent />
                <img src={logo} alt="Logo do RollShare" />
                <section>
                    <h1>Login</h1>
                    <form onSubmit={handleSubmit}>
                        <InputComponent 
                        label={"Usuário"}
                        name={"usuario"}
                        value={formData.usuario.value}
                        type={"text"}
                        onChange={handleChange}
                        placeholder={"Digite seu email de usuário"}
                        />
                        <InputComponent 
                        label={"Senha"}
                        name={"senha"}
                        value={formData.senha.value}
                        type={"password"}
                        onChange={handleChange}
                        placeholder={"Digite sua senha"}
                        />
                        <label>Esqueceu sua senha? <span onClick={()=>navigate("/esqueceu-a-senha")}>Clique aqui</span>
                        </label>
                        <ButtonPrimaryComponent text={"Entrar"}/>
                    </form>
                </section>
            </div>
        </ContainerScreen>
    )
}