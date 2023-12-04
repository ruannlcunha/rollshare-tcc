import { useState } from "react";
import { axiosInstance } from "../../base/axios-instance.api";
import { useToast } from "../../toast/use-toast.hook";

export function usePesquisarUsuarios() {
    const [usuarios, setUsuarios] = useState([])
    const { toastError } = useToast();

    async function _pesquisarUsuarios(nome, page) {
        const response = await axiosInstance.get(
        `/usuarios/pesquisar?nome=${nome}&page=${page}&sort=id,desc`,
        );
        return response.data;
    }

    async function pesquisarUsuarios(nome, page) {
        try {
            const _usuarios = await _pesquisarUsuarios(nome, page)
            setUsuarios(_usuarios)
        } catch (error) {
            toastError(error)
        }
    }

    return { usuarios, setUsuarios, pesquisarUsuarios };
}
