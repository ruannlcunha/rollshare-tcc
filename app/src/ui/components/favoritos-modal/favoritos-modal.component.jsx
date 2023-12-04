import "./favoritos-modal.style.css"
import { ModalComponent, FavoritoComponent } from ".."
import { useListarFavoritos } from "../../../hook";
import { useEffect, useState } from "react";
 
export function FavoritosModal({isOpen, setIsOpen}) {
    const { favoritos, listarFavoritos } = useListarFavoritos();
    const [pagina, setPagina] = useState(0);

    useEffect(()=> {
        listarFavoritos(pagina)
    },[pagina])

    
    function renderSetaEsquerda() {
        if(pagina>0) {
            return (
                <div 
                className="favoritos-seta-esquerda"
                onClick={()=> setPagina(old=>old-1)}
                ></div>
            )
        }
        return <div></div>
    }

    function renderSetaDireita() {
        if((pagina+1)!==favoritos.totalPages) {
            return (
                <div 
                className="favoritos-seta-direita"
                onClick={()=> setPagina(old=>old+1)
                }></div>
            )
        }
        return <div></div>
    }

    function renderPaginacao() {
        if(!favoritos.empty&&favoritos.totalPages>1) {
            return (
                <footer className="favoritos-paginacao">
                    {renderSetaEsquerda()}
                    <h1>{(pagina+1)}</h1>
                    {renderSetaDireita()}
                </footer>
            )
        }
    }

    return (
        <ModalComponent isOpen={isOpen} setIsOpen={setIsOpen}>
            <div className="favoritos-modal">
                <h1>Meus Favoritos</h1>
                <section>
                {favoritos.content?
                    favoritos.content.map(fav=> {
                        return (
                            <FavoritoComponent usuario={fav}/>
                            )
                        })
                :null}
                {renderPaginacao()}
                </section>
            </div>
        </ModalComponent>
    )

}