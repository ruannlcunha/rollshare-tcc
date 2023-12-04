import { axiosInstance } from "../../base/axios-instance.api";
import { useNavigate } from "react-router-dom";
import { useToast } from "../../toast/use-toast.hook";

export function useConfirmarEmail() {
  const navigate = useNavigate()
  const { toastSuccess, toastWarning } = useToast();

  async function _confirmarEmail(token) {
    await axiosInstance.post(
      "/usuarios/confirmar-email/publico",
      {
        token
      }
    );
  }

  async function confirmarEmail(token) {
    try {
      await _confirmarEmail(token)
      toastSuccess("Email confirmado!")
      navigate(`/login`)
    } catch (error) {
      toastWarning(error)
    }
  }

  return { confirmarEmail };
}
