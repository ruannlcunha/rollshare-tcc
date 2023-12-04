import { axiosInstance } from "../../base/axios-instance.api";
import { useNavigate } from "react-router-dom";
import { useToast } from "../../toast/use-toast.hook";

export function useEditarConteudo() {
  const navigate = useNavigate()
  const { toastSuccess, toastWarning } = useToast();

  async function _editarConteudo(id, descricao, categorias) {
    await axiosInstance.put(
      `/conteudos/${id}`,
      {
        id,
        descricao, 
        categorias
      }
    );
  }

  async function editarConteudo(id, descricao, categorias) {
    try {
      await _editarConteudo(id, descricao, categorias)
      navigate(`/conteudo/${id}`)
      toastSuccess("Conteúdo alterado!")
    } catch (error) {
      toastWarning(error)
    }
  }

  return { editarConteudo };
}
