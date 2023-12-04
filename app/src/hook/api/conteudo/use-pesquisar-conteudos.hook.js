import { useState } from "react";
import { axiosInstance } from "../../base/axios-instance.api";
import { useToast } from "../../toast/use-toast.hook";

export function usePesquisarConteudos() {
    const [conteudos, setConteudos] = useState([])
    const { toastError } = useToast();

    async function _pesquisarConteudos(filtro, categoria, page) {
        const response = await axiosInstance.get(
        `/conteudos/pesquisar?filtro=${filtro}&categoria=${categoria}&page=${page}&sort=id,desc`,
        );
        return response.data;
    }

    async function pesquisarConteudos(filtro, categoria, page) {
        try {
            if(!categoria) categoria = "";
            const _conteudos = await _pesquisarConteudos(filtro, categoria, page)
            setConteudos(_conteudos)
        } catch (error) {
            toastError(error)
        }
    }

    return { conteudos, setConteudos, pesquisarConteudos };
}
