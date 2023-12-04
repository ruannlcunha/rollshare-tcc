import "./avaliar-modal.style.css"
import { ModalComponent, PergaminhoComponent, InputComponent } from "../"
import { useForm, useAvaliar } from "../../../hook";
import { useState, useEffect } from "react";
 
export function AvaliarModal({conteudoId, isOpen, setIsOpen}) {
    const forSize = [1,2,3,4,5]
    const [formActive, setFormActive] = useState(false);
    const [nota, setNota] = useState(0);
    const { avaliar } = useAvaliar();
    const {formData, setFormData, handleChange} = useForm({descricao: {value: "", error: ""}});

    useEffect(()=> {
        setFormActive(false)
        setNota(0)
        setFormData({descricao: {value: "", error: ""}})
        setTimeout(handleFormActive, 1500 )
    },[isOpen])

    function handleFormActive() {
        setFormActive(true)
    }

    async function handleSubmit(event) {
        event.preventDefault();
        await avaliar({
            id: conteudoId,
            descricao: formData.descricao.value,
            nota: nota
        })
        setIsOpen(false)
    }


    function renderForm() {
        if(formActive) {
            return (
                <form onSubmit={handleSubmit}>
                    <InputComponent 
                    label={"Descrição"}
                    name={"descricao"}
                    value={formData.descricao.value}
                    type={"text"}
                    onChange={handleChange}
                    placeholder={"Digite sua descrição"}
                    />
                    <label>Nota</label>
                    <div className="nota-container">
                        {forSize.map(i=> {
                            if(i<=nota) return <div key={i} onClick={()=>setNota(i)} className="nota-ativa"></div>
                            return (<div key={i} onClick={()=>setNota(i)}></div>)
                        })}
                    </div>
                    <button onClick={handleSubmit}>Avaliar</button>
                </form>
            )
        }
    }

    return (
        <ModalComponent isOpen={isOpen} setIsOpen={setIsOpen}>
            <PergaminhoComponent>
                <div className="avaliar">
                    {renderForm()}
                </div>
            </PergaminhoComponent>
        </ModalComponent>
    )

}