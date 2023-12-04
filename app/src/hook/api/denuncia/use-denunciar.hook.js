import { axiosInstance } from "../../base/axios-instance.api";
import { useToast } from "../../toast/use-toast.hook";

export function useDenunciar() {
    const { toastSuccess, toastWarning } = useToast();

    async function _denunciar(id, motivo) {
        await axiosInstance.post(
        `/denuncias/${id}`, {
            motivo
        }
        );
    }

    async function denunciar(id, motivo) {
        try {
            await _denunciar(id, motivo)
            toastSuccess("Denúncia enviada para os administradores.")

        } catch (error) {
            toastWarning(error)
        }
    }

    return { denunciar };
}
