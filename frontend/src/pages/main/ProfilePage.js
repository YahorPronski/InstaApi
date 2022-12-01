import UserInfo from "../../components/main/UserInfo";
import ButtonLink from "../../components/common/ButtonLink";
import Post from "../../components/main/Post";
import "../../assets/styles/pages/main/profile.scss";
import Header from "../../components/main/Header";

function ProfilePage() {
    return (
        <div>
            {/* use outlet instead */}
            <Header/>

            <div className="profile-page">
                <UserInfo/>
                <ButtonLink url="/posts/new">Add post <span className="icon plus small"></span></ButtonLink>
                <Post/>
                <Post/>
            </div>
        </div>
    );
}

export default ProfilePage;