import { RouterProvider } from "react-router-dom";
import { GlobalUserProvider } from "./context/global-user.context";
import { GlobalAdmProvider } from "./context/global-adm.context";
import { router } from "./router";
import { ToastContainer } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';

function App() {
  return (
    <GlobalUserProvider>
      <GlobalAdmProvider>
        <ToastContainer />
        <RouterProvider router={router}/>
      </GlobalAdmProvider>
    </GlobalUserProvider>
  )
  
}

export default App
