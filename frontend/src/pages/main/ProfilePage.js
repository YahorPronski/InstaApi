import useAuthContext from "../../context/useAuthContext";
import UserInfo from "../../components/main/UserInfo";
import ButtonLink from "../../components/common/ButtonLink";
import Post from "../../components/main/Post";
import "../../assets/styles/pages/main/profile.scss";

function ProfilePage() {
    const { user } = useAuthContext();

    return (
        <div className="profile-page">
            <UserInfo/>
            <ButtonLink url="/posts/new">Add post <span className="icon plus small"></span></ButtonLink>
            <Post/>
            <Post/>
        </div>
    );
}

export default ProfilePage;