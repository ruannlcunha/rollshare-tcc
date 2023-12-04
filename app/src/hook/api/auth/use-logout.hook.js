import { axiosInstance } from "../../base/axios-instance.api";
import { useNavigate } from "react-router-dom";
import { useToast } from "../../toast/use-toast.hook";
import useGlobalUser from "../../../context/global-user.context"
import useGlobalAdm from "../../../context/global-adm.context"

export function useLogout() {
  const navigate = useNavigate();
  const [,setGlobalUser] = useGlobalUser();
  const [,setGlobalAdm] = useGlobalAdm();
  const { toastSuccess, toastError } = useToast();

  async function _logout() {
    await axiosInstance.post("/logout");
  }

  async function logout() {
    try {
      await _logout()
      toastSuccess("Tchau! Até logo!")
      setGlobalUser(null)
      setGlobalAdm(null)
      navigate("/")
    } catch (error) {
      toastError(error)
    }
  }

  return { logout };
}
