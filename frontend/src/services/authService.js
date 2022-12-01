import API from '../services/api';

const authTokensName = "authTokens";

export const saveTokens = (data) => {
    localStorage.setItem(authTokensName, JSON.stringify({
        tokenType: data.tokenType,
        accessToken: data.accessToken,
        refreshToken: data.refreshToken,
    }));
};

export const getAuthorizedUserId = async () => {
    const userId = await getUserIdByAccessToken();
    if (userId) return userId;

    await refreshTokens();
    return getUserIdByAccessToken();
}

const getUserIdByAccessToken = async () => {
    try {
        const accessToken = getAuthTokens()?.accessToken;
        if (!accessToken) return;

        return await API.get('auth/validateToken', {
            params: {
                token: accessToken
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

