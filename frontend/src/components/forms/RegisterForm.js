import { useRef, useState } from 'react';
import { flushSync } from 'react-dom';
import { useNavigate } from "react-router-dom";
import API from '../../api';
import TextBlock, { Type as TextBlockType } from "../core/TextBlock";
import '../../assets/styles/components/form/core.scss';
import '../../assets/styles/components/form/entry.scss';

function RegisterForm() {
    const navigate = useNavigate();

    const [firstName, setFirstName] = useState('');
    const [secondName, setSecondName] = useState('');
    const [email, setEmail] = useState('');
    const [username, setUsername] = useState('');
    const [password, setPassword] = useState('');
    const [passwordConfirm, setPasswordConfirm] = useState('');

    const [errorMessage, setErrorMessage] = useState('');
    const errorMessageRef = useRef(null);

    function validateForm() {
        if (!email || !username || !password) {
            return 'Not all required fields are filled';
        }
        const emailRegex = /^(([^<>()[\].,;:\s@"]+(\.[^<>()[\].,;:\s@"]+)*)|(".+"))@(([^<>()[\].,;:\s@"]+\.)+[^<>()[\].,;:\s@"]{2,})$/i;
        if (emailRegex.test(email) === false) {
            return 'Email is not valid';
        }
        if (password !== passwordConfirm) {
            return 'Password mismatch';
        }
        return null;
    }

    function handleSubmit(event) {
        event.preventDefault();
        setErrorMessage("");

        const error = validateForm();
        if (error) {
            showErrorMessage(error);
            return;
        }

        const user = {
            firstName: firstName,
            secondName: secondName,
            email: email,
            username: username,
            password: password,
        };

        API.post('auth/register', user)
            .then(() => navigate("/login", {state : {registerSuccess: true}}))
            .catch((error) => {
                if (error.response?.status === 409) {
                    showErrorMessage("Username already exists");
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
                <div className="fields-row">
                    <div>
                        <label>First Name</label>
                        <input
                            type="text"
                            value={firstName}
                            onChange={event => setFirstName(event.target.value)}/>
                    </div>
                    <div>
                        <label>Last Name</label>
                        <input
                            type="text"
                            value={secondName}
                            onChange={event => setSecondName(event.target.value)}/>
                    </div>
                </div>

                <label className="required">Email</label>
                <input
                    type="text"
                    value={email}
                    onChange={event => setEmail(event.target.value)}
                />

                <label className="required">Username</label>
                <input
                    type="text"
                    value={username}
                    onChange={event => setUsername(event.target.value)}
                />

                <label className="required">Password</label>
                <input
                    type="password"
                    value={password}
                    onChange={event => setPassword(event.target.value)}
                />

                <label className="required">Confirm Password</label>
                <input
                    type="password"
                    value={passwordConfirm}
                    onChange={event => setPasswordConfirm(event.target.value)}
                />

                <input type="submit" value="Sign up"/>
            </form>

            <div ref={errorMessageRef}>
                {errorMessage && <TextBlock type={TextBlockType.ERROR}>{errorMessage}</TextBlock>}
            </div>
        </div>
    );
}

export default RegisterForm;