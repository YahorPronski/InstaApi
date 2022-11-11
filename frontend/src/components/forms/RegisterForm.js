import React from 'react';
import '../../assets/styles/components/forms/entry.scss';

function LoginForm(props) {
    return (
        <form className="entry-form">
            <div className="fields-row">
                <label>First Name
                    <input type="text" name="usename"/>
                </label>
                <label>Last Name
                    <input type="text" name="usename"/>
                </label>
            </div>
            <label>Email
                <input type="text" name="usename"/>
            </label>
            <label>Username
                <input type="text" name="usename"/>
            </label>
            <label>Password
                <input type="password" name="password"/>
            </label>
            <label>Confirm Password
                <input type="password" name="password"/>
            </label>
            <input type="submit" value="Sign up"/>
        </form>
    );
}

export default LoginForm;