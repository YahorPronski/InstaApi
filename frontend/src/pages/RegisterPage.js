import { useEffect } from "react";
import { useNavigate } from 'react-router-dom';
import useAuthContext from "../context/useAuthContext";
import Teaser from "../components/Teaser";
import RegisterForm from "../components/form/RegisterForm";
import '../assets/styles/pages/register.scss';
import logo from '../assets/images/logo.png';

function RegisterPage() {
    const navigate = useNavigate();
    const { user } = useAuthContext();

    useEffect(() => {
        if (user) navigate("/home");
    }, []);

    return (
        <div className="register-page">
            <Teaser imgSize="large" imgSrc={logo} imgAlt="logo" row>
                Sign up to InstaApp
            </Teaser>
            <RegisterForm/>
        </div>
    );
}

export default RegisterPage;