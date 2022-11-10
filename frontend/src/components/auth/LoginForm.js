import React from 'react';
import '../../assets/styles/components/auth/loginform.scss';

function LoginForm(props) {
    return <h1 className="login-form">Hello, {props.name}</h1>;
}

export default LoginForm;