import Teaser, { ImageSize as TeaserImageSize, Direction as TeaserDirection } from "../components/core/Teaser";
import RegisterForm from "../components/forms/RegisterForm";
import '../assets/styles/pages/register.scss';
import logo from '../assets/images/logo.png';

function RegisterPage() {
    return (
        <div className="register-page">
            <Teaser
                dir={TeaserDirection.ROW}
                imgSize={TeaserImageSize.LARGE}
                imgSrc={logo}
                imgAlt="logo">
                Sign up to InstaApp
            </Teaser>
            <RegisterForm/>
        </div>
    );
}

export default RegisterPage;