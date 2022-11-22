import { useState, useEffect } from 'react';
import { getAuthorizedUser } from '../services/authService'
import AuthContext from './AuthContext'

export const AuthProvider = ({ children }) => {
    const [user, setUser] = useState(null);
    const [contextLoaded, setContextLoaded] = useState(false);

    const updateAuthContext = async () => {
        const currentUser = await getAuthorizedUser();
        setUser(currentUser);
    }

    useEffect(() => {
        updateAuthContext().then(() => setContextLoaded(true));
    }, []);

    return <AuthContext.Provider value={{ user, updateAuthContext }}>{contextLoaded && children}</AuthContext.Provider>;
};

export default AuthProvider