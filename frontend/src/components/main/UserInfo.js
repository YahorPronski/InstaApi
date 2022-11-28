import "../../assets/styles/components/main/userinfo.scss"
import logo from '../../assets/images/logo.png';

const UserInfo = () => {
    return (
        <div className="user-info">
            <h1 className="user-info__username">username</h1>

            <div className="user-info__main-wrapper">
                <img className="user-info__avatar" src={logo}/>
                <div>
                    <p>152</p>
                    <p>Posts</p>
                </div>
                <div>
                    <p>1044</p>
                    <p>Followers</p>
                </div>
                <div>
                    <p>963</p>
                    <p>Following</p>
                </div>
            </div>

        </div>
    );
};

export default UserInfo;