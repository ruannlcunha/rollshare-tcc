import { axiosInstance } from "../../base/axios-instance.api";
import { useNavigate } from "react-router-dom";
import { useToast } from "../../toast/use-toast.hook";

export function useSolicitarRecuperacao() {
  const navigate = useNavigate()
  const { toastSuccess, toastWarning } = useToast();

  async function _solicitarRecuperacao(email) {
    await axiosInstance.post(
      "/usuarios/solicitar-alterar-senha/publico",
      {
        email
      }
    );
  }

  async function solicitarRecuperacao(email) {
    try {
      await _solicitarRecuperacao(email)
      toastSuccess("Token enviado, cheque seu email.")
      navigate(`/alterar-senha`)
    } catch (error) {
      toastWarning(error)
    }
  }

  return { solicitarRecuperacao };
}
