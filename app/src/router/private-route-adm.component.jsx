import useGlobalAdm from "../context/global-adm.context";
import { Navigate } from "react-router-dom";

export function PrivateRouteAdm({ Screen }) {
  const [adm] = useGlobalAdm();

  if (adm) {
    return <Screen />;
  }
  return <Navigate to={"/"} />;
}
