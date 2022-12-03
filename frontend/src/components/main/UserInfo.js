import "../../assets/styles/components/main/userinfo.scss"
import "../../assets/styles/common/icons.scss"
import * as UserService from "../../services/userService";
import {useEffect, useState} from "react";

const UserInfo = ({userId}) => {
    const [user, setUser] = useState({});

    useEffect(() => {
        UserService.getUserById(userId).then(setUser);
    },[]);

    return (<>{user &&
        <div className="user-info">
            <div className="user-info__avatar">
                {user.avatar ? <img src={user.avatar} alt="avatar"/>
                             : <span className="icon user extra-large"></span>}
            </div>

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