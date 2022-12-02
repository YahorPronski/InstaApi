import API from '../services/api';
import * as TokenService from './tokenService';

export const login = (credentials, onSuccess, onError) => {
    API.post('auth/login', credentials)
        .then((response) => {
            TokenService.saveTokens(response.data);
            onSuccess(response);
        })
        .catch(onError);
};

export const logout = () => {
    TokenService.removeTokens();
};

export const register = (userData, onSuccess, onError) => {
    API.post('auth/register', userData)
        .then(onSuccess)
        .catch(onError);
};

export const getAuthorizedUserId = async () => {
    const userId = await getUserIdByAccessToken();
    if (userId) return userId;

    await TokenService.refreshTokens();
    return getUserIdByAccessToken();
}

export const getAuthConfig = () => {
    const tokens = TokenService.getTokens();
    if (!tokens) return;
    return {
        headers: {
            Authorization: `${tokens.tokenType} ${tokens.accessToken}`
        }
    };
};

const getUserIdByAccessToken = async () => {
    try {
        const accessToken = TokenService.getTokens()?.accessToken;
        if (!accessToken) return;

        return (await API.get('auth/validateToken', {
            params: {
                token: accessToken
            }
        })).data;
    } catch (error) {
        console.log(error);
    }
};



