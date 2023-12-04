import "./seta-voltar.style.css"
import { useNavigate } from "react-router-dom";

export function SetaVoltarComponent() {
    const navigate = useNavigate();
 
    return (
        <button className="seta-voltar" onClick={()=>navigate(-1)}></button>
    )

}