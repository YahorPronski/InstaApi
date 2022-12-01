import "../../assets/styles/components/main/userinfo.scss"
import logo from '../../assets/images/logo.png';
import useAuthContext from "../../context/useAuthContext";
import {getUserById} from "../../services/userService";
import {useEffect, useState} from "react";

const UserInfo = () => {
    const {userId} = useAuthContext();
    const [user, setUser] = useState({});

    useEffect(() => {
        getUserById(userId).then(response => setUser(response.data));
    },[]);

    return (<>{user &&
        <div className="user-info">
            <img className="user-info__avatar" src={logo}/>

            <h1 className="user-info__title">{user.firstName} {user.lastName}</h1>
            <p className="user-info__subtitle">{user.email}</p>

            <div className="user-info__subs-info">
                <div>
                    <p>{user.postsCount}</p>
                    <p>posts</p>
                </div>
                <div>
                    <p>{user.followersCount}</p>
                    <p>followers</p>
                </div>
                <div>
                    <p>{user.followingCount}</p>
                    <p>following</p>
                </div>
            </div>
        </div>
    }</>);
};

export default UserInfo;