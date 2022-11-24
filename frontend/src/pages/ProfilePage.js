import useAuthContext from "../context/useAuthContext";

function ProfilePage() {
    const { user } = useAuthContext();

    return (
        <div>
            <h1>Home page</h1>
        </div>
    );
}

export default ProfilePage;