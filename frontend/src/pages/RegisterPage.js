import Teaser, { ImageSizes as TeaserImageSizes, Directions as TeaserDirections } from "../components/core/Teaser";
import RegisterForm from "../components/forms/RegisterForm";
import '../assets/styles/pages/register.scss';
import '../assets/styles/core/flex.scss';
import logo from '../assets/images/logo.png';

function RegisterPage() {
    return (
        <div className="register-page">
            <div className="header">
                <Teaser
                    dir={TeaserDirections.ROW}
                    imgSize={TeaserImageSizes.LARGE}
                    imgSrc={logo}
                    imgAlt="logo">
                    Sign up to InstaApp
                </Teaser>
            </div>
            <RegisterForm/>
        </div>
    );
}

export default RegisterPage;