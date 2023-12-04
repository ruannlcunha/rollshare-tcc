import "./perfil-info.style.css"
import coracaoIcon from "../../../assets/img/coracao_icon.png"
import d20Icon from "../../../assets/img/d20_icon.png"
import rollpointIcon from "../../../assets/img/rollpoint_icon.png"
import cadeadoIcon from "../../../assets/img/cadeado_icon.png"
import logoutIcon from "../../../assets/img/logout_icon.png"
import estrelaIcon from "../../../assets/img/estrela_icon.png"
import { useLogout, useFavoritar, useSolicitarRecuperacao } from "../../../hook"
import userIcon from "../../../assets/img/user_icon.png"
import userCapa from "../../../assets/img/capa_padrao.png"
import { COSMETICOS } from "../../../constants/cosmeticos.constant"
import useGlobalUser from "../../../context/global-user.context"
import { useState } from "react"
import { FavoritosModal} from "../"
import { useNavigate } from "react-router-dom"

export function PerfilInfoComponent({ usuario, fetch }) {
    const { favoritar } = useFavoritar();
    const { logout } = useLogout();
    const navigate = useNavigate();
    const [ globalUser ] = useGlobalUser();
    const [favoritosIsOpen, setFavoritosIsOpen] = useState(false);
    const { solicitarRecuperacao } = useSolicitarRecuperacao();

    async function handleFavoritar() {
        await favoritar(usuario.id)
        await fetch(usuario.id)
    }

    async function handleAlterarSenha() {
        await solicitarRecuperacao(usuario.email)
    }

    function renderRollPoints() {
        if(globalUser===usuario.id) {
            return (
                <label>
                    <img src={rollpointIcon} alt="RollPoints " />
                    {usuario.rollPoints} RollPoints
                </label>
            )
        }
    }

    function handleEditar() {
        navigate(`/perfil/${usuario.id}/editar`)
    }

    function renderEditar() {
        if(globalUser===usuario.id) {
            return <button onClick={handleEditar} className="perfil-botao-editar"></button>
        }
        else if(usuario.foiFavoritado){
            return <button onClick={handleFavoritar} className="perfil-botao-favoritado"></button>
        }
        
        return <button onClick={handleFavoritar} className="perfil-botao-favoritar"></button>
    }

    function renderOpcoes() {
        if(globalUser===usuario.id) {
            return (
                <section className="perfil-opcoes">
                <button onClick={handleAlterarSenha}>
                    <img src={cadeadoIcon} alt="ícone de cadeado" />
                    Alterar Senha
                </button>
                <button onClick={logout}>
                    <img src={logoutIcon} alt="ícone de porta de saída" />
                    Logout
                </button>
            </section>
            )
        }
    }

    function renderImagemPerfil() {
        if(usuario.imagemPerfil) {
            return (<div className="perfil-foto" style={{backgroundImage: `url(${usuario.imagemPerfil})`}}></div>)
        }
        return <div className="perfil-foto" style={{backgroundImage: `url(${userIcon})`}}></div>
    }

    function renderImagemCapa() {
        if(usuario.imagemCapa) {
            return <div className="perfil-capa" style={{backgroundImage: `url(${usuario.imagemCapa})`}}></div>
        }
        return <div className="perfil-capa" style={{backgroundImage: `url(${userCapa})`}}></div>
    }

    return (
        <section className="perfil-info">
            {renderImagemCapa()}
            <section className="perfil-usuario-nomes">
                {renderImagemPerfil()}
                <section>
                    <h1>
                        {usuario.apelido}
                        <img src={COSMETICOS[usuario.insigniaId]} alt="Insígnia " />
                    </h1>
                    <h3>@{usuario.apelido}</h3>
                </section>

                {renderEditar()}
            </section>

            <section className="perfil-usuario-infos">
                <label>
                    <img src={coracaoIcon} alt="Coração " />
                    {usuario.quantidadeDeFavoritos} Favoritos
                </label>
                <label>
                    <img src={d20Icon} alt="Dado de 20 lados " />
                    {usuario.quantidadeDeConteudos} Conteúdos
                </label>
                {renderRollPoints()}
                <label className="ver-meus-favoritos" onClick={()=>setFavoritosIsOpen(true)}>
                    <img src={estrelaIcon} alt="Estrela verde" />
                    Meus Favoritos
                </label>
            </section>
            {renderOpcoes()}
            <FavoritosModal isOpen={favoritosIsOpen} setIsOpen={setFavoritosIsOpen}/>
        </section>
    )

}