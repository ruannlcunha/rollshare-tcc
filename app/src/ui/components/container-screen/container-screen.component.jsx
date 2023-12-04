import "./container-screen.style.css"
import { NevoaComponent } from "../../components";

export function ContainerScreen({children}) {
    return (
        <div className="container-screen">
            {children}
            <NevoaComponent />
        </div>
    )
}