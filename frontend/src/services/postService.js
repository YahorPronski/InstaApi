import API from "./api";
import {getAuthConfig} from "./authService";

export const savePost = async (post) => {
    try {
        await API.post("/posts", post, getAuthConfig());
    } catch (error) {
        console.log(error);
    }
}

export const getPostsByUserId = async (userId) => {
    let config = getAuthConfig();
    config.params = {
        userId: userId
    };
    try {
        return (await API.get("/posts", config)).data;
    } catch (error) {
        console.log(error);
    }
    return [];
}