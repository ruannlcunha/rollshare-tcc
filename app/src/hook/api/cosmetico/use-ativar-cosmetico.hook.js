import { axiosInstance } from "../../base/axios-instance.api";
import { useToast } from "../../toast/use-toast.hook";

export function useAtivarCosmetico() {
    const { toastSuccess, toastWarning } = useToast();

    async function _ativarCosmetico(id) {
        const response = await axiosInstance.put(
        `/cosmeticos/${id}`,
        );
        return response.data;
    }

    async function ativarCosmetico(id) {
        try {
            const _cosmetico = await _ativarCosmetico(id)
            toastSuccess("Cosmético ativado.")
            return _cosmetico
        } catch (error) {
            toastWarning(error)
        }
    }

    return { ativarCosmetico };
}
