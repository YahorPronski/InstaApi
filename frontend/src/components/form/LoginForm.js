import { useState, useEffect, useRef } from 'react';
import { useLocation, useNavigate } from "react-router-dom";
import useAuthContext from "../../context/useAuthContext";
import * as AuthService from "../../services/authService";
import TextInput from "./fields/TextInput";
import SubmitButton from "./fields/SubmitButton";
import Alert from "../common/Alert";
import '../../assets/styles/components/form/entryform.scss';

const LoginForm = () => {
    const navigate = useNavigate();
    const { state } = useLocation();
    const { updateAuthContext } = useAuthContext();

    const [credentials, setCredentials] = useState({username: '', password: ''});
    const [errorMessage, setErrorMessage] = useState('');
    const errorMessageRef = useRef(null);

    useEffect(() => {
        if (errorMessage) {
            errorMessageRef.current.scrollIntoView();
        }
    }, [errorMessage]);

    const handleSubmit = () => {
        setErrorMessage("");
        if (!validateForm()) return;

        const onSuccess = () => {
            updateAuthContext().then(() => navigate("/profile"));
        };

        const onError = (error) => {
            if (error.response?.status === 401) {
                setCredentials(creds => ({...creds, password: ''}));
                setErrorMessage("Invalid username or password");
            } else {
                setErrorMessage("Unexpected error, try again later");
            }
        };

        AuthService.login(credentials, onSuccess, onError)
    };

    const handleInput = (event) => {
        setCredentials(creds => ({
            ...creds,
            [event.target.name]: event.target.value,
        }));
    };

    const validateForm = () => {
        const isBlank = (str) => !str || !str.trim().length;
        if (isBlank(credentials.username) || isBlank(credentials.password)) {
            setErrorMessage("Please enter username and password");
            return false;
        }
        return true;
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

            <div ref={errorMessageRef}>
                {errorMessage && <Alert type="error">{errorMessage}</Alert>}
            </div>

            {!errorMessage && state?.registerSuccess &&
                <Alert type="success">You have successfully registered</Alert>
            }
        </div>
    );
}

export default LoginForm;