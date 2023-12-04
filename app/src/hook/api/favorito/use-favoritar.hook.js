import { axiosInstance } from "../../base/axios-instance.api";
import { useToast } from "../../toast/use-toast.hook";

export function useFavoritar() {
  const { toastWarning } = useToast();

  async function _favoritar(id) {
    await axiosInstance.post(
      `/favoritos/${id}`
    );
  }

  async function favoritar(id) {
    try {
      await _favoritar(id)
    } catch (error) {
      toastWarning(error)
    }
  }

  return { favoritar };
}
