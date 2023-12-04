import { useState } from "react";
import { axiosInstance } from "../../base/axios-instance.api";
import { useToast } from "../../toast/use-toast.hook";

export function useListarConteudos() {
    const [conteudos, setConteudos] = useState([])
    const { toastError } = useToast();

    async function _listarConteudos(id, page) {
        const response = await axiosInstance.get(
        `/conteudos/${id}?page=${page}&sort=id,desc`,
        );
        return response.data;
    }

    async function listarConteudos(id, page) {
        try {
            const _conteudos = await _listarConteudos(id, page)
            setConteudos(_conteudos)
        } catch (error) {
            toastError(error)
        }
    }

    return { conteudos, setConteudos, listarConteudos };
}
