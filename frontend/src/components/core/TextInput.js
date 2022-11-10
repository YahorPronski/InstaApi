import React from 'react';
import '../../assets/styles/components/auth/loginform.scss';

function TextInput(props) {
    return (
        <label>First name:
            <input type={props.type} name={props.name}/>
        </label>
    );
}

export default LoginForm;