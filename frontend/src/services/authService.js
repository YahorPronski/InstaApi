import API from '../services/api';

const authTokensName = "authTokens";

export const saveTokens = (data) => {
    localStorage.setItem(authTokensName, JSON.stringify({
        accessToken: `${data.tokenType} ${data.accessToken}`,
        refreshToken: `${data.tokenType} ${data.refreshToken}`,
    }));
};

export const getAuthorizedUser = async () => {
    const user = await getUserByAccessToken();
    if (user) return user;

    await refreshTokens();
    return getUserByAccessToken();
}

const getUserByAccessToken = async () => {
    try {
        const accessToken = getAuthTokens()?.accessToken;
        if (!accessToken) return;

        return await API.get('users/authorized', {
            headers: {
                'Authorization': accessToken
            }
        });
    } catch (error) { }
};

const refreshTokens = async () => {
    try {
        const refreshToken = getAuthTokens()?.refreshToken;
        if (!refreshToken) return;

        const response = await API.get('auth/refreshToken', {
            params: {
                token: refreshToken
            }
        });
        saveTokens(response.data);
    } catch (error) { }
};

const getAuthTokens = () => {
    const authTokens = localStorage.getItem(authTokensName);
    if (!authTokens) return;
    return JSON.parse(authTokens);
}

