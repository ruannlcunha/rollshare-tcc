import { axiosInstance } from "../../base/axios-instance.api";
import { useToast } from "../../toast/use-toast.hook";

export function useAvaliar() {
  const { toastSuccess, toastWarning } = useToast();

  async function _avaliar({ id, descricao, nota }) {
    await axiosInstance.post(
      `/avaliacoes/${id}`,
      {
        descricao,
        nota
      }
    );
  }

  async function avaliar({ id, descricao, nota }) {
    try {
      await _avaliar({ id, descricao, nota })
      toastSuccess("Conteúdo avaliado!")
    } catch (error) {
      toastWarning(error)
    }
  }

  return { avaliar };
}
