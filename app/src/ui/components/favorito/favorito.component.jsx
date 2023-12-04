import "./favorito.style.css"
import { COSMETICOS } from "../../../constants/cosmeticos.constant"
import userIcon from "../../../assets/img/user_icon.png"
import { useNavigate } from "react-router-dom";

export function FavoritoComponent({ usuario }) {
    const navigate = useNavigate();

    function renderImagemPerfil() {
        if(usuario.imagemDePerfil) {
            return (
                <button className="conteudo-usuario"
                style={{backgroundImage: `url(${usuario.imagemDePerfil})`}}
                onClick={()=>{navigate(`/perfil/${usuario.id}`)}}
                ></button>
            )
        }
        return (
            <button className="conteudo-usuario"
            style={{backgroundImage: `url(${userIcon})`}}
            onClick={()=>{navigate(`/perfil/${usuario.id}`)}}
            ></button>
        )
    }

    return (
        <div className="favorito">
            {renderImagemPerfil()}
            <section>
            <h3 onClick={()=>{navigate(`/perfil/${usuario.id}`)}}>
                {usuario.apelido}
                <img src={COSMETICOS[usuario.insigniaId]} alt="Insígnia" />
            </h3>
            <h4>@{usuario.nomeDeUsuario}</h4>
            </section>
        </div>
    )
}