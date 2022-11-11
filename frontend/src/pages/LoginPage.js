import React from 'react';
import { Link } from 'react-router-dom';
import LoginForm from "../components/forms/LoginForm";
import '../assets/styles/pages/entry.scss';
import '../assets/styles/core/link.scss';
import logo from '../assets/images/logo.png';

function LoginPage(props) {
    return (
        <div className="entry-page">
            <img className="logo" src={logo} alt="logo"/>
            <h1 className="heading">Sign in to InstaApp</h1>
            <LoginForm name="yahorrrrr"/>
            <div className="secondary-block">
                New to InstaApp? <Link className="regular-link" to="/register">Create an account.</Link>
            </div>
        </div>
    );
}

export default LoginPage;