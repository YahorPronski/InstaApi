import Teaser from "../common/Teaser";
import {removeTokens} from "../../services/authService";
import "../../assets/styles/components/main/header.scss";
import "../../assets/styles/common/icons.scss";
import logo from "../../assets/images/logo.png"
import useAuthContext from "../../context/useAuthContext";

const Header = () => {
    const {updateAuthContext} = useAuthContext();

    const logout = () => {
        removeTokens();
        updateAuthContext();
    };

    return (
        <header>
            <Teaser imgSrc={logo} imgSize="small" row>InstaApp</Teaser>
            <span onClick={logout} className="icon logout medium cursor-ptr"></span>
        </header>
    );
};

export default Header;