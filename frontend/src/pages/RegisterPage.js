import React from 'react';
import RegisterForm from "../components/forms/RegisterForm";
import '../assets/styles/pages/entry.scss';
import logo from '../assets/images/logo.png';

function RegisterPage(props) {
    return (
        <div className="entry-page">
            <div className="inline-header">
                <img className="logo" src={logo} alt="logo"/>
                <h1 className="heading">Sign up to InstaApp</h1>
            </div>
            <RegisterForm name="yahorrrrr"/>
        </div>
    );
}

export default RegisterPage;