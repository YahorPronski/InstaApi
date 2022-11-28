import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import LoginPage from "./pages/auth/LoginPage";
import RegisterPage from "./pages/auth/RegisterPage";
import ProfilePage from "./pages/main/ProfilePage";
import Authorized from "./components/wrap/Authorized";
import Unauthorized from "./components/wrap/Unauthorized";

function App() {
    return (
        <BrowserRouter>
            <Routes>
                <Route path="/" element={<Navigate to="/profile" replace/>}/>
                <Route path="/login" element={<Unauthorized><LoginPage/></Unauthorized>}/>
                <Route path="/register" element={<Unauthorized><RegisterPage/></Unauthorized>}/>
                <Route path="/profile" element={<Authorized><ProfilePage/></Authorized>}/>
            </Routes>
        </BrowserRouter>
    );
}

export default App;
