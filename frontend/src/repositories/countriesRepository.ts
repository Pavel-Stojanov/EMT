import api from '../api';
import type {Country} from '../types';

export const countriesRepository = {
    async getAll() {
        const response = await api.get<Country[]>('/countries');
        return response.data;
    },

    async getById(id: string) {
        const response = await api.get<Country>(`/countries/${id}`);
        return response.data;
    },
};
