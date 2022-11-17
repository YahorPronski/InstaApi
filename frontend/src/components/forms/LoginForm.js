import '../../assets/styles/components/form/entry.scss';

function LoginForm() {
    return (
        <form className="entry-form">
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