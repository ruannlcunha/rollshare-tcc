import "./denunciar-modal.style.css"
import { ModalComponent, ButtonPrimaryComponent } from ".."
import { useDenunciar, useForm } from "../../../hook";
import { useEffect } from "react";
 
export function DenunciarModal({conteudoId, isOpen, setIsOpen})  {
    const {formData, setFormData, handleChange} = useForm({motivo: {value: "", error: ""}});
    const { denunciar } = useDenunciar();

    useEffect(()=> {
        setFormData({motivo: {value: "", error: ""}})
    },[isOpen])

    async function handleSubmit(event) {
        event.preventDefault();
        await denunciar(conteudoId, formData.motivo.value)
        setIsOpen(false)
    }


    function renderForm() {
            return (
                <form onSubmit={handleSubmit}>
                    <label>Motivo da denúncia</label>
                    <textarea
                    name={"motivo"}
                    value={formData.motivo.value}
                    onChange={handleChange}
                    placeholder={"Digite o motivo de sua denúncia."}
                    />
                    <ButtonPrimaryComponent text={"Denunciar"} onClick={handleSubmit}/>
                </form>
            )
    }

    return (
        <ModalComponent isOpen={isOpen} setIsOpen={setIsOpen}>
                <div className="denunciar-modal">
                    {renderForm()}
                </div>
        </ModalComponent>
    )

}