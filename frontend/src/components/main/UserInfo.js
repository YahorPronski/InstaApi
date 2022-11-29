import "../../assets/styles/components/main/userinfo.scss"
import logo from '../../assets/images/logo.png';

const UserInfo = () => {
    return (
        <div className="user-info">

            <img className="user-info__avatar" src={logo}/>

            <h1 className="user-info__fullname">Name Surname</h1>
            <p className="user-info__username">username</p>

            <div className="user-info__subs-info">
                <div>
                    <p>152</p>
                    <p>posts</p>
                </div>
                <div>
                    <p>1044</p>
                    <p>followers</p>
                </div>
                <div>
                    <p>963</p>
                    <p>following</p>
                </div>
            </div>
        </div>
    );
};

export default UserInfo;