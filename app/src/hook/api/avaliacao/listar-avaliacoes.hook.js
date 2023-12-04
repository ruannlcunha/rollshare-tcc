import { useState } from "react";
import { axiosInstance } from "../../base/axios-instance.api";
import { useToast } from "../../toast/use-toast.hook";

export function useListarAvaliacoes() {
    const [avaliacoes, setAvaliacoes] = useState([])
    const { toastError } = useToast();

    async function _listarAvaliacoes(id, page) {
        const response = await axiosInstance.get(
        `/avaliacoes/${id}?size=4&page=${page}&sort=id,desc`,
        );
        return response.data;
    }

    async function listarAvaliacoes(id, page) {
        try {
            const _avaliacoes = await _listarAvaliacoes(id, page)
            setAvaliacoes(_avaliacoes)
        } catch (error) {
            toastError(error)
        }
    }

    return { avaliacoes, setAvaliacoes, listarAvaliacoes };
}
