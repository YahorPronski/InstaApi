import API from '../services/api';
import {getAuthHeader} from "./authService";

export const getUserById = async (id) => {
    try {
        return await API.get(`users/${id}`, {
            headers: getAuthHeader()
        });
    } catch (error) { }
}