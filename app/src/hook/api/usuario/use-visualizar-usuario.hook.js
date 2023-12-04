import { useState } from "react";
import { axiosInstance } from "../../base/axios-instance.api";
import { useToast } from "../../toast/use-toast.hook";

export function useVisualizarUsuario() {
    const [usuario, setUsuario] = useState({})
    const { toastError } = useToast();

    async function _visualizarUsuario(id) {
        const response = await axiosInstance.get(
        `/usuarios/${id}`,
        );
        return response.data;
    }

    async function visualizarUsuario(id) {
        try {
            const _usuario = await _visualizarUsuario(id)
            setUsuario(_usuario)
        } catch (error) {
            toastError(error)
        }
    }

    return { usuario, setUsuario, visualizarUsuario };
}
