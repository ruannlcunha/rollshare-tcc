import { axiosInstance } from "../../base/axios-instance.api";
import { useNavigate } from "react-router-dom";
import { useToast } from "../../toast/use-toast.hook";

export function useAlterarPerfil() {
  const navigate = useNavigate()
  const { toastSuccess, toastWarning } = useToast();

  async function _alterarPerfil({ nomeDeUsuario, apelido, email, imagemDeCapa, imagemDePerfil }) {
    const response = await axiosInstance.put(
      "/usuarios",
      {
        nomeDeUsuario,
        apelido,
        email,
        imagemDeCapa,
        imagemDePerfil
      }
    );
    return response.data;
  }

  async function alterarPerfil({ nomeDeUsuario, apelido, email, imagemDeCapa, imagemDePerfil }) {
    try {
      const _usuario = await _alterarPerfil({ nomeDeUsuario, apelido, email, imagemDeCapa, imagemDePerfil })
      navigate(`/perfil/${_usuario.id}`)
      toastSuccess("Perfil atualizado!")
      return _usuario;
    } catch (error) {
      toastWarning(error)
    }
  }

  return { alterarPerfil };
}
