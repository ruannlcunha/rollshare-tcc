import { axiosInstance } from "../../base/axios-instance.api";
import { useToast } from "../../toast/use-toast.hook";

export function useEscolherCategorias() {
    const { toastError } = useToast();

    async function _escolherCategorias(nome) {
        const response = await axiosInstance.post(
        "/usuarios/categorias",
        {
            nome
        }
        );
        return response.data;
    }

    async function escolherCategorias(setCategorias, nome) {
        try {
            const _categoria = await _escolherCategorias(nome)
            setCategorias(oldCategorias=> [...oldCategorias, _categoria])
        } catch (error) {
            toastError(error)
        }
    }

    return { escolherCategorias };
}
