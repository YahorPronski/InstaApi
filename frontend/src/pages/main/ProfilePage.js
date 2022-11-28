import useAuthContext from "../../context/useAuthContext";
import "../../assets/styles/pages/main/profile.scss";

function ProfilePage() {
    const { user } = useAuthContext();

    return (
        <div className="profile-page">
            <h1>Home page</h1>
        </div>
    );
}

export default ProfilePage;