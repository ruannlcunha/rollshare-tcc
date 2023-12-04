import { axiosInstance } from "../../base/axios-instance.api";
import { useToast } from "../../toast/use-toast.hook";

export function useEditarAvaliacao() {
  const { toastSuccess, toastWarning } = useToast();

  async function _editarAvaliacao({ id, descricao, nota }) {
    await axiosInstance.put(
      `/avaliacoes/${id}`,
      {
        descricao,
        nota
      }
    );
  }

  async function editarAvaliacao({ id, descricao, nota }) {
    try {
      await _editarAvaliacao({ id, descricao, nota })
      toastSuccess("Avaliação alterada!")
    } catch (error) {
      toastWarning(error)
    }
  }

  return { editarAvaliacao };
}
