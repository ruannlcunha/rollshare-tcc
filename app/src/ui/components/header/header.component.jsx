import "./header.style.css"
import { useNavigate } from "react-router-dom";
import { BarraPesquisaComponent } from "../../components/";
import useGlobalUser from "../../../context/global-user.context";
import useGlobalAdm from "../../../context/global-adm.context"

export function HeaderComponent() {
    const [ globalUser ] = useGlobalUser();
    const [ globalAdm ] = useGlobalAdm();
    const navigate = useNavigate();

    function renderDenuncias() {
        if(globalAdm) {
            return <li onClick={()=>navigate("/denuncias")} className="header-denuncias"></li>
        }
    }

    return (
        <header className="header-container">
            <button className="logo-button" onClick={()=>navigate("/feed")}></button>
            <BarraPesquisaComponent />
            <ul>
                {renderDenuncias()}
                <li onClick={()=>navigate("/loja")} className="header-loja"></li>
                <li onClick={()=>navigate(`/perfil/${globalUser}`)} className="header-perfil"></li>
            </ul>
        </header>
    )
}