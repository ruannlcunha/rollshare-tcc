import { useState } from "react";
import { axiosInstance } from "../../base/axios-instance.api";
import { useToast } from "../../toast/use-toast.hook";

export function useVisualizarConteudo() {
    const [conteudo, setConteudo] = useState({})
    const { toastError } = useToast();

    async function _visualizarConteudo(id) {
        const response = await axiosInstance.get(
        `/conteudos/${id}/detalhar`,
        );
        return response.data;
    }

    async function visualizarConteudo(id) {
        try {
            const _conteudo = await _visualizarConteudo(id)
            setConteudo(_conteudo)
        } catch (error) {
            toastError(error)
        }
    }

    return { conteudo, setConteudo, visualizarConteudo };
}
