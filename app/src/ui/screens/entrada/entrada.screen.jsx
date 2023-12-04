import "./entrada.style.css"
import logo from "../../../assets/img/rollshare_logo1.png"
import { useNavigate } from "react-router-dom";
import { ContainerScreen } from "../../components";
import { useEffect } from "react";
import useGlobalUser from "../../../context/global-user.context";

export function EntradaScreen() {
    const navigate = useNavigate();
    const [, setGlobalUser] = useGlobalUser();

    useEffect(()=> {
        setGlobalUser(null)
    },[])

    return (
        <ContainerScreen>
            <div className="entrada-fundo">
                <img src={logo} alt="Logo escrito RollShare" />
                <button onClick={()=>navigate("/login")}>Entrar</button>
                <button onClick={()=>navigate("/cadastro")}>Cadastrar</button>
            </div>
        </ContainerScreen>
    )
}