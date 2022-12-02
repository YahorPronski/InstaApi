import API from "./api";
import {getAuthConfig} from "./authService";

export const savePost = async (post) => {
    try {
        await API.post("/posts", post, getAuthConfig())
    } catch (error) {
        console.log(error);
    }
}