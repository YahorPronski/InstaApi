import React from 'react';
import { Link } from 'react-router-dom';
import LoginForm from "../components/forms/LoginForm";
import TextBlock from "../components/core/TextBlock";
import '../assets/styles/pages/entry.scss';
import '../assets/styles/core/link.scss';
import logo from '../assets/images/logo.png';

function LoginPage() {
    return (
        <div className="entry-page">
            <img className="logo" src={logo} alt="logo"/>
            <h1 className="heading">Sign in to InstaApp</h1>
            <LoginForm/>
            <TextBlock>
                New to InstaApp? <Link className="regular-link" to="/register">Create an account.</Link>
            </TextBlock>
        </div>
    );
}

export default LoginPage;