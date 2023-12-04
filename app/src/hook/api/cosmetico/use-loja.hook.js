import { useState } from "react";
import { axiosInstance } from "../../base/axios-instance.api";
import { useToast } from "../../toast/use-toast.hook";

export function useLoja() {
    const [cosmeticos, setCosmeticos] = useState([])
    const { toastError } = useToast();

    async function _loja(tipo) {
        const response = await axiosInstance.get(
        `/cosmeticos/loja?tipo=${tipo}`,
        );
        return response.data;
    }

    async function loja(tipo) {
        try {
            const _cosmeticos = await _loja(tipo)
            setCosmeticos(_cosmeticos)
        } catch (error) {
            toastError(error)
        }
    }

    return { cosmeticos, setCosmeticos, loja };
}
