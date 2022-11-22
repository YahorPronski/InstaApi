import { useEffect } from "react";
import { useNavigate } from "react-router-dom";
import useAuthContext from "../context/useAuthContext";

function HomePage() {
    const navigate = useNavigate();
    const { user } = useAuthContext();

    useEffect(() => {
        if (!user) navigate("/login");
    }, []);

    return (
        <div>
            <h1>Home page</h1>
        </div>
    );
}

export default HomePage;