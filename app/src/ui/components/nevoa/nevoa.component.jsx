import "./nevoa.style.css"
import { useEffect, useState } from "react";

export function NevoaComponent() {
    const [active, setActive] = useState(true);

    useEffect(()=> {
        setTimeout(handleTimeout, 1800 )
    },[])

    function handleTimeout() {
        setActive(false)
    }

    function renderNevoa() {
        return active ? (
        <div className="nevoa"></div>
        ) : null
    }

    return (
        renderNevoa()
    )
}