import { BrowserRouter, Routes, Route, Navigate } from "react-router-dom";
import LoginPage from "./pages/LoginPage";
import RegisterPage from "./pages/RegisterPage";
import ProfilePage from "./pages/ProfilePage";
import Authorized from "./components/auth/Authorized";
import Unauthorized from "./components/auth/Unauthorized";

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
