import { useState, useEffect } from 'react';
import { getAuthorizedUserId } from '../services/authService'
import AuthContext from './AuthContext'

export const AuthProvider = ({ children }) => {
    const [userId, setUserId] = useState(null);
    const [contextLoaded, setContextLoaded] = useState(false);

    const updateAuthContext = async () => {
        setUserId(await getAuthorizedUserId());
    }

    useEffect(() => {
        updateAuthContext().then(() => setContextLoaded(true));
    }, []);

    return <AuthContext.Provider value={{ userId, updateAuthContext }}>{contextLoaded && children}</AuthContext.Provider>;
};

export default AuthProvider