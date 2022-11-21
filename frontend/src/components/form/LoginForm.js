import { useState } from 'react';
import { useLocation } from "react-router-dom";
import API from '../../services/api';
import { handleLoginSuccess } from '../../services/AuthService';
import TextInput from "./items/TextInput";
import SubmitButton from "./items/SubmitButton";
import Alert from "../Alert";
import '../../assets/styles/components/form/entryform.scss';

const LoginForm = () => {
    const [credentials, setCredentials] = useState({username: '', password: ''});
    const [errorMessage, setErrorMessage] = useState('');
    const { state } = useLocation();

    const validateForm = () => {
        const isBlank = (str) => !str || !str.trim().length;
        if (isBlank(credentials.username) || isBlank(credentials.password)) {
            setErrorMessage("Please enter username and password");
            return false;
        }
        return true;
    };

    const handleSubmit = () => {
        setErrorMessage("");
        if (!validateForm()) return;

        API.post('auth/login', credentials)
            .then(handleLoginSuccess)
            .catch((error) => {
                if (error.response?.status === 401) {
                    setCredentials(creds => ({...creds, password: ''}));
                    setErrorMessage("Invalid username or password");
                } else {
                    setErrorMessage("Unexpected error, try again later");
                }
            });
    };

    const handleInput = (event) => {
        setCredentials(creds => ({
            ...creds,
            [event.target.name]: event.target.value,
        }));
    };

    return (
        <div>
            <form className="entry-form">
                <TextInput
                    label="Username"
                    name="username"
                    value={credentials.username}
                    onChange={handleInput}/>
                <TextInput
                    label="Password"
                    name="password"
                    value={credentials.password}
                    onChange={handleInput}
                    hidden/>
                <SubmitButton text="Sign in" onClick={handleSubmit}/>
            </form>

            {errorMessage && <Alert type="error">{errorMessage}</Alert>}

            {!errorMessage && state?.registerSuccess &&
                <Alert type="success">You have successfully registered</Alert>
            }
        </div>
    );
}

export default LoginForm;