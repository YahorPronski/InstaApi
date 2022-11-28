import { Link } from 'react-router-dom';
import Teaser from "../../components/common/Teaser";
import LoginForm from "../../components/form/LoginForm";
import Alert from "../../components/common/Alert";
import '../../assets/styles/pages/auth/login.scss';
import logo from '../../assets/images/logo.png';

function LoginPage() {
    return (
        <div className="login-page">
            <Teaser imgSize="large" imgSrc={logo} imgAlt="logo">
                Sign in to InstaApp
            </Teaser>
            <LoginForm/>
            <Alert>
                New to InstaApp? <Link to="/register">Create an account.</Link>
            </Alert>
        </div>
    );
}

export default LoginPage;