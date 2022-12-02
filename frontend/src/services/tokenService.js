import API from "./api";

const authTokensName = "authTokens";

export const saveTokens = (tokens) => {
    localStorage.setItem(authTokensName, JSON.stringify({
        tokenType: tokens.tokenType,
        accessToken: tokens.accessToken,
        refreshToken: tokens.refreshToken,
    }));
};

export const getTokens = () => {
    const authTokens = localStorage.getItem(authTokensName);
    if (!authTokens) return;
    return JSON.parse(authTokens);
};

export const removeTokens = () => {
    localStorage.removeItem(authTokensName);
};

export const refreshTokens = async () => {
    try {
        const refreshToken = getTokens()?.refreshToken;
        if (!refreshToken) return;

        const response = await API.get('auth/refreshToken', {
            params: {
                token: refreshToken
            }
        });
        saveTokens(response.data);
    } catch (error) {
        console.log(error);
    }
};

