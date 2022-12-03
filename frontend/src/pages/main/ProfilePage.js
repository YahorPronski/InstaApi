import UserInfo from "../../components/main/UserInfo";
import ButtonLink from "../../components/common/ButtonLink";
import "../../assets/styles/pages/main/profile.scss";
import Header from "../../components/main/Header";
import useAuthContext from "../../context/useAuthContext";
import UserPosts from "../../components/main/UserPosts";

function ProfilePage() {
    const {userId} = useAuthContext();


    return (
        <>
            {/* use outlet instead */}
            <Header/>

            <div className="profile-page">
                <UserInfo userId={userId}/>
                <ButtonLink url="/posts/new">Add post <span className="icon plus small"></span></ButtonLink>
                <UserPosts userId={userId}/>
            </div>
        </>
    );
}

export default ProfilePage;