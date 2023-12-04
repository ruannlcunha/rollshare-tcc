import { axiosInstance } from "../../base/axios-instance.api";
import { useNavigate } from "react-router-dom";
import { useToast } from "../../toast/use-toast.hook";

export function useAdicionarConteudo() {
  const navigate = useNavigate()
  const { toastSuccess, toastWarning } = useToast();

  async function _adicionarConteudo(descricao, categorias, imagens) {
    const response = await axiosInstance.post(
      `/conteudos/`,
      {
        descricao,
        categorias,
        imagens
      }
    );
    return response.data;
  }

  async function adicionarConteudo(descricao, categorias, imagens) {
    try {
      const conteudo = await _adicionarConteudo(descricao, categorias, imagens)
      navigate(`/conteudo/${id}`)
      toastSuccess("Conteúdo adicionado!")
      return conteudo;
    } catch (error) {
      toastWarning(error)
    }
  }

  return { adicionarConteudo };
}
