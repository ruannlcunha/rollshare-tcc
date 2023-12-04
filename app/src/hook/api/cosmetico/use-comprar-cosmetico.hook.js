import { axiosInstance } from "../../base/axios-instance.api";
import { useToast } from "../../toast/use-toast.hook";

export function useComprarCosmetico() {
    const { toastSuccess, toastWarning } = useToast();

    async function _comprarCosmetico(id) {
        const response = await axiosInstance.post(
        `/cosmeticos/${id}`,
        );
        return response.data;
    }

    async function comprarCosmetico(id, cosmeticos) {
        try {
            const _cosmetico = await _comprarCosmetico(id)
            toastSuccess("Cosmético comprado!")
            
            return cosmeticos.filter(cos=> cos.id!==id)
        } catch (error) {
            toastWarning(error)
        }
    }

    return { comprarCosmetico };
}
