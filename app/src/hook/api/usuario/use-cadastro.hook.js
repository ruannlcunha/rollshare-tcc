import { axiosInstance } from "../../base/axios-instance.api";
import { useNavigate } from "react-router-dom";
import { useToast } from "../../toast/use-toast.hook";

export function useCadastro() {
  const navigate = useNavigate()
  const { toastSuccess, toastWarning } = useToast();

  async function _cadastro({ nomeDeUsuario, apelido, email, senha, confirmacaoDeSenha }) {
    await axiosInstance.post(
      "/usuarios",
      {
        nomeDeUsuario,
        apelido,
        email,
        senha,
        confirmacaoDeSenha
      }
    );
  }

  async function cadastro({ nomeDeUsuario, apelido, email, senha, confirmacaoDeSenha }) {
    try {
      await _cadastro({ nomeDeUsuario, apelido, email, senha, confirmacaoDeSenha })
      navigate("/confirmar-email")
      toastSuccess("Cadastrado com sucesso! Cheque seu email.")
    } catch (error) {
      toastWarning(error)
    }
  }

  return { cadastro };
}
