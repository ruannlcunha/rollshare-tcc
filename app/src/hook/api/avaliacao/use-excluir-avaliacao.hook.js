import { axiosInstance } from "../../base/axios-instance.api";
import { useToast } from "../../toast/use-toast.hook";

export function useExcluirAvaliacao() {
  const { toastSuccess, toastWarning } = useToast();

  async function _excluirAvaliacao(id) {
    await axiosInstance.delete(
      `/avaliacoes/${id}`
    );
  }

  async function excluirAvaliacao(id) {
    try {
      await _excluirAvaliacao(id)
      toastSuccess("Avaliação excluída!")
    } catch (error) {
      toastWarning(error)
    }
  }

  return { excluirAvaliacao };
}
