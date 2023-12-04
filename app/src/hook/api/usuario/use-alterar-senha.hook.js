import { axiosInstance } from "../../base/axios-instance.api";
import { useToast } from "../../toast/use-toast.hook";
import { useNavigate } from "react-router-dom";

export function useAlterarSenha() {
  const { toastSuccess, toastWarning } = useToast();
  const navigate = useNavigate();

  async function _alterarSenha(senha, confirmacaoDeSenha, tokenSeguranca) {
    await axiosInstance.put(
      "/usuarios/alterar-senha/publico",
      {
        senha,
        confirmacaoDeSenha,
        tokenSeguranca
      }
    );
  }

  async function alterarSenha(senha, confirmacaoDeSenha, tokenSeguranca) {
    try {
      await _alterarSenha(senha, confirmacaoDeSenha, tokenSeguranca)
      toastSuccess("Senha alterada!")
      navigate("/login")
    } catch (error) {
      toastWarning(error)
    }
  }

  return { alterarSenha };
}
