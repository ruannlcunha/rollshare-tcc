import { axiosInstance } from "../../base/axios-instance.api";
import { useNavigate } from "react-router-dom";
import useGlobalUser from "../../../context/global-user.context"
import useGlobalAdm from "../../../context/global-adm.context"
import { useToast } from "../../toast/use-toast.hook";

export function useLogin() {
  const navigate = useNavigate();
  const [,setGlobalUser] = useGlobalUser();
  const [,setGlobalAdm] = useGlobalAdm();
  const { toastSuccess, toastError } = useToast();

  async function _login({ username, password }) {
    const response = await axiosInstance.post(
      "/login",
      {},
      {
        auth: {
          username,
          password,
        },
      }
    );
    return response.data;
  }

  async function login({ username, password }) {
    try {
      const user = await _login({ username, password })
      setGlobalUser(user.id)
      setGlobalAdm(user.isAdmin)
      toastSuccess("Bem vindo!")
      navigate("/feed")
    } catch (error) {
      toastError(error)
    }
  }

  return { login };
}
