import { useEffect } from "react";
import { Link, useNavigate } from 'react-router-dom';
import useAuthContext from "../context/useAuthContext";
import Teaser from "../components/Teaser";
import LoginForm from "../components/form/LoginForm";
import Alert from "../components/Alert";
import '../assets/styles/pages/login.scss';
import logo from '../assets/images/logo.png';

function LoginPage() {
    const navigate = useNavigate();
    const { user } = useAuthContext();

    useEffect(() => {
        if (user) navigate("/home");
    }, []);

    return (
        <div className="login-page">
            <Teaser imgSize="large" imgSrc={logo} imgAlt="logo">
                Sign in to InstaApp
            </Teaser>
            <LoginForm/>
            <Alert>
                New to InstaApp? <Link to="/register">Create an account.</Link>
            </Alert>
        </div>
    );
}

export default LoginPage;