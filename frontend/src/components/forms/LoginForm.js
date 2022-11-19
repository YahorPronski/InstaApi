import { useRef, useState } from 'react';
import { flushSync } from 'react-dom';
import { useLocation } from "react-router-dom";
import API from '../../api';
import TextBlock, { Type as TextBlockType } from "../core/TextBlock";
import '../../assets/styles/components/form/entry.scss';

function LoginForm() {
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');

    const [errorMessage, setErrorMessage] = useState('');
    const errorMessageRef = useRef(null);

    const {state} = useLocation();

    function handleSubmit(event) {
        event.preventDefault();
        setErrorMessage("");

        if (!username || !password) {
            showErrorMessage("Please enter username and password");
            return;
        }

        const creds = {
            username: username,
            password: password,
        };

        API.post('auth/login', creds)
            .then((response) => {
                const tokenType = response.data.tokenType;
                const accessToken = response.data.accessToken;
                const refreshToken = response.data.refreshToken;

                localStorage.setItem("accessToken", tokenType + " " + accessToken);
                localStorage.setItem("refreshToken", tokenType + " " + refreshToken);
            })
            .catch((error) => {
                if (error.response?.status === 401) {
                    setUsername("");
                    setPassword("");
                    showErrorMessage("Invalid username or password");
                } else {
                    showErrorMessage("Unexpected error, try again later");
                }
            });
    }

    function showErrorMessage(message) {
        flushSync(() => setErrorMessage(message));
        errorMessageRef.current.scrollIntoView();
    }

    return (
        <div>
            <form onSubmit={handleSubmit} className="entry-form">
                <label>Username
                    <input
                        type="text"
                        value={username}
                        onChange={event => setUsername(event.target.value)}/>
                </label>
                <label>Password
                    <input
                        type="password"
                        value={password}
                        onChange={event => setPassword(event.target.value)}/>
                </label>
                <input type="submit" value="Sign in"/>
            </form>
            <div ref={errorMessageRef}>
                {errorMessage && <TextBlock type={TextBlockType.ERROR}>{errorMessage}</TextBlock>}
            </div>
            {!errorMessage && state?.registerSuccess &&
                <TextBlock type={TextBlockType.SUCCESS}>
                    You have successfully registered
                </TextBlock>
            }
        </div>
    );
}

export default LoginForm;