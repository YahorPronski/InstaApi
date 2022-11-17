import { Link } from 'react-router-dom';
import Teaser, { ImageSize as TeaserImageSize, Direction as TeaserDirection } from "../components/core/Teaser";
import LoginForm from "../components/forms/LoginForm";
import TextBlock from "../components/core/TextBlock";
import '../assets/styles/pages/login.scss';
import logo from '../assets/images/logo.png';

function LoginPage() {
    return (
        <div className="login-page">
            <Teaser
                dir={TeaserDirection.COLUMN}
                imgSize={TeaserImageSize.LARGE}
                imgSrc={logo}
                imgAlt="logo">
                Sign in to InstaApp
            </Teaser>
            <LoginForm/>
            <TextBlock>
                New to InstaApp? <Link to="/register">Create an account.</Link>
            </TextBlock>
        </div>
    );
}

export default LoginPage;