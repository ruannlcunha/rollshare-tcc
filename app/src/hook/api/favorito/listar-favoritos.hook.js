import { useState } from "react";
import { axiosInstance } from "../../base/axios-instance.api";
import { useToast } from "../../toast/use-toast.hook";

export function useListarFavoritos() {
    const [favoritos, setFavoritos] = useState([])
    const { toastError } = useToast();

    async function _listarFavoritos(page) {
        const response = await axiosInstance.get(
        `/favoritos?page=${page}`,
        );
        return response.data;
    }

    async function listarFavoritos(page) {
        try {
            const _favoritos = await _listarFavoritos(page)
            setFavoritos(_favoritos)
        } catch (error) {
            toastError(error)
        }
    }

    return { favoritos, setFavoritos, listarFavoritos };
}
