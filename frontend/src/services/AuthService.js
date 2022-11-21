export const handleLoginSuccess = (response) => {
    localStorage.setItem("authTokens", JSON.stringify({
        accessToken: `${response.data.tokenType} ${response.data.accessToken}`,
        refreshToken: `${response.data.tokenType} ${response.data.refreshToken}`,
    }));
};