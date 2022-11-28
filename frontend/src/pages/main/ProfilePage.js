import useAuthContext from "../../context/useAuthContext";
import UserInfo from "../../components/main/UserInfo";
import "../../assets/styles/pages/main/profile.scss";

function ProfilePage() {
    const { user } = useAuthContext();

    return (
        <div className="profile-page">
            <UserInfo/>
        </div>
    );
}

export default ProfilePage;