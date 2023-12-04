import { axiosInstance } from "../../base/axios-instance.api";
import { useToast } from "../../toast/use-toast.hook";

export function useExcluirConteudo() {
  const { toastSuccess, toastWarning } = useToast();

  async function _excluirConteudo(id) {
    await axiosInstance.delete(
      `/conteudos/${id}`
    );
  }

  async function excluirConteudo(id) {
    try {
      await _excluirConteudo(id)
      toastSuccess("Conteúdo excluído!")
    } catch (error) {
      toastWarning(error)
    }
  }

  return { excluirConteudo };
}
