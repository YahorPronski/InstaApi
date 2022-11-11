import React from 'react';
import LoginForm from "../components/forms/LoginForm";
import '../assets/styles/pages/loginpage.scss';
import logo from '../assets/images/logo.png';

function LoginPage(props) {
    return (
        <div className="login-page-container">
            <img className="logo" src={logo} alt="logo"/>
            <h1 className="heading">Sign in to InstaApp</h1>
            <LoginForm name="yahorrrrr"/>
        </div>
    );
}

export default LoginPage;