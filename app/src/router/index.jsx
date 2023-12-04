import { createBrowserRouter } from "react-router-dom";
import { PrivateRouteAdm } from "./private-route-adm.component";
import { PrivateRouteUsuario } from "./private-route-usuario.component";
import {
  EntradaScreen,
  LoginScreen,
  CadastroScreen,
  FeedScreen,
  LojaScreen,
  PerfilScreen,
  ConteudoDetalhadoScreen,
  EditarConteudoScreen,
  SolicitarRecuperacaoScreen,
  AlterarSenhaScreen,
  ConfirmarEmailScreen,
  DenunciasScreen,
  EditarPerfilScreen,
  PesquisaScreen
} from "../ui/screens";

export const router = createBrowserRouter([
  {
    path: "/",
    element: <EntradaScreen />,
  },
  {
    path: "/login",
    element: <LoginScreen />,
  },
  {
    path: "/cadastro",
    element: <CadastroScreen />,
  },
  {
    path: "/confirmar-email",
    element: <ConfirmarEmailScreen />,
  },
  {
    path: "/esqueceu-a-senha",
    element: <SolicitarRecuperacaoScreen />,
  },
  {
    path: "/alterar-senha",
    element: <AlterarSenhaScreen />,
  },
  {
    path: "/feed",
    element: <PrivateRouteUsuario Screen={FeedScreen} />,
  },
  {
    path: "/pesquisar/:filtro?/:categorias?",
    element: <PrivateRouteUsuario Screen={PesquisaScreen} />,
  },
  {
    path: "/loja",
    element: <PrivateRouteUsuario Screen={LojaScreen} />,
  },
  {
    path: "/perfil/:id",
    element: <PrivateRouteUsuario Screen={PerfilScreen} />,
  },
  {
    path: "/perfil/:id/editar",
    element: <PrivateRouteUsuario Screen={EditarPerfilScreen} />,
  },
  {
    path: "/conteudo/:id",
    element: <PrivateRouteUsuario Screen={ConteudoDetalhadoScreen} />,
  },
  {
    path: "/conteudo/:id/editar",
    element: <PrivateRouteUsuario Screen={EditarConteudoScreen} />,
  },
  {
    path: "/denuncias",
    element: <PrivateRouteAdm Screen={DenunciasScreen} />,
  }
]);
