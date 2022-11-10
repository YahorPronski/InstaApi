import React from 'react';
import '../../assets/styles/components/auth/loginform.scss';

function LoginForm(props) {
    return (
        <form className="login-form">
            <label>Username
                <input type="text" name="usename"/>
            </label>
            <label>Password
                <input type="password" name="password"/>
            </label>
            <input type="submit" value="Sign in"/>
        </form>
    );
}

export default LoginForm;