import "./button-primary.style.css"

export function ButtonPrimaryComponent({text, onClick}) {
    return (
        <button className="button-primary" onClick={onClick}>{text}</button>
    )
}