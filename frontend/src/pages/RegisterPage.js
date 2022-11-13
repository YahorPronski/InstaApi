import React from 'react';
import RegisterForm from "../components/forms/RegisterForm";
import '../assets/styles/pages/entry.scss';
import '../assets/styles/core/flex.scss';
import logo from '../assets/images/logo.png';

function RegisterPage() {
    return (
        <div className="entry-page">
            <div className="flex flex-row justify-content-between align-items-center">
                <img className="logo" src={logo} alt="logo"/>
                <h1 className="heading">Sign up to InstaApp</h1>
            </div>
            <RegisterForm/>
        </div>
    );
}

export default RegisterPage;