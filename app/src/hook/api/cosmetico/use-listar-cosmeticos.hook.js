import { useState } from "react";
import { axiosInstance } from "../../base/axios-instance.api";
import { useToast } from "../../toast/use-toast.hook";

export function useListarCosmeticos() {
    const [cosmeticos, setCosmeticos] = useState([])
    const { toastError } = useToast();

    async function _listarCosmeticos(tipo) {
        const response = await axiosInstance.get(
        `/cosmeticos?tipo=${tipo}`,
        );
        return response.data;
    }

    async function listarCosmeticos(tipo) {
        try {
            const _cosmeticos = await _listarCosmeticos(tipo)
            setCosmeticos(_cosmeticos)
        } catch (error) {
            toastError(error)
        }
    }

    return { cosmeticos, setCosmeticos, listarCosmeticos };
}
