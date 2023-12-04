import "./editar-avaliacao-modal.style.css"
import { ModalComponent, PergaminhoComponent, InputComponent } from ".."
import { useForm, useEditarAvaliacao } from "../../../hook";
import { useState, useEffect } from "react";
 
export function EditarAvaliacaoModal({avaliacaoId, isOpen, setIsOpen, descricaoAvaliacao, notaAvaliacao, fetch}) {
    const forSize = [1,2,3,4,5]
    const [formActive, setFormActive] = useState(false);
    const [nota, setNota] = useState(notaAvaliacao);
    const { editarAvaliacao } = useEditarAvaliacao();
    const {formData, setFormData, handleChange} = useForm({descricao: {value: descricaoAvaliacao, error: ""}});

    useEffect(()=> {
        setFormActive(false)
        setNota(notaAvaliacao)
        setFormData({descricao: {value: descricaoAvaliacao, error: ""}})
        setTimeout(handleFormActive, 1500 )
    },[isOpen])

    function handleFormActive() {
        setFormActive(true)
    }

    async function handleSubmit(event) {
        event.preventDefault();
        await editarAvaliacao({
            id: avaliacaoId,
            descricao: formData.descricao.value,
            nota: nota
        })
        await fetch()
        setIsOpen(false)
    }


    function renderForm() {
        if(formActive) {
            return (
                <form onSubmit={handleSubmit}>
                <label>Editar Avaliação</label>
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
                    <button onClick={handleSubmit}>Editar</button>
                </form>
            )
        }
    }

    return (
        <ModalComponent isOpen={isOpen} setIsOpen={setIsOpen}>
            <PergaminhoComponent>
                <div className="editar-avaliacao">
                    {renderForm()}
                </div>
            </PergaminhoComponent>
        </ModalComponent>
    )

}