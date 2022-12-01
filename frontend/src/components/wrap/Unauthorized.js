import { Navigate } from "react-router-dom";
import useAuthContext from "../../context/useAuthContext";

const Unauthorized = ({children}) => {
    const { user } = useAuthContext();
    if (user) {
        return <Navigate to="/profile" replace/>
    }
    return children;
};

export default Unauthorized;