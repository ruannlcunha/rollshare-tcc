import "./anuncio.style.css"
import anuncioBeyond from "../../../assets/img/anuncio_beyond.png"
import anuncioTormenta from "../../../assets/img/anuncio_tormenta.png"

export function AnuncioComponent() {
    const anuncios = [
        {image: anuncioBeyond, url: "https://www.dndbeyond.com"},
        {image: anuncioTormenta, url: "https://jamboeditora.com.br"}]

    function randomAnuncio() {
        const randomNumber = Math.floor(Math.random() * anuncios.length);
        return anuncios.at(randomNumber)
    }

    function renderAnuncio() {
        const anuncio = randomAnuncio()
        return (
            <a 
            target="_blank"
            className="anuncio-container"
            href={anuncio.url}
            style={{backgroundImage: `url(${anuncio.image})`}}
            >
            </a>
        )
    }

    return (
        <>
        {
            renderAnuncio()
        }
        </>
        
    )

}