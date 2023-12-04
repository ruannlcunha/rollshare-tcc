import { axiosInstance } from "../../base/axios-instance.api";
import { useToast } from "../../toast/use-toast.hook";

export function useResolverDenuncia() {
    const { toastSuccess, toastWarning } = useToast();

    async function _resolverDenuncia(id, remover) {
        const response = await axiosInstance.put(
        `/denuncias/resolver/${id}`, {
            remover
        }
        );
        return response.data;
    }

    async function resolverDenuncia(id, remover) {
        try {
            await _resolverDenuncia(id, remover)
            toastSuccess("Denúncia resolvida.")

        } catch (error) {
            toastWarning(error)
        }
    }

    return { resolverDenuncia };
}
