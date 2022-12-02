import API from '../services/api';
import {getAuthConfig} from "./authService";

export const getUserById = async (id) => {
    try {
        return await API.get(`users/${id}`, getAuthConfig());
    } catch (error) {
        console.log(error);
    }
}