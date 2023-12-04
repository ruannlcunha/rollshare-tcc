import { useState } from "react";
import { axiosInstance } from "../../base/axios-instance.api";
import { useToast } from "../../toast/use-toast.hook";

export function useListarCategorias() {
    const [categorias, setCategorias] = useState([])
    const { toastError } = useToast();

    async function _listarCategorias() {
        const response = await axiosInstance.get(
        "/usuarios/categorias",
        );
        return response.data;
    }

    async function listarCategorias() {
        try {
            const _categorias = await _listarCategorias()
            setCategorias(_categorias)
        } catch (error) {
            toastError(error)
        }
    }

    return { categorias, setCategorias, listarCategorias };
}
