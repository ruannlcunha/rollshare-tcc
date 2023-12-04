import { useState } from "react";
import { axiosInstance } from "../../base/axios-instance.api";
import { useToast } from "../../toast/use-toast.hook";

export function useListarDenuncias() {
    const [denuncias, setDenuncias] = useState([])
    const { toastError } = useToast();

    async function _listarDenuncias(page) {
        const response = await axiosInstance.get(
        `/denuncias?page=${page}`,
        );
        return response.data;
    }

    async function listarDenuncias(page) {
        try {
            const _denuncias = await _listarDenuncias(page)
            setDenuncias(_denuncias)
        } catch (error) {
            toastError(error)
        }
    }

    return { denuncias, setDenuncias, listarDenuncias };
}
