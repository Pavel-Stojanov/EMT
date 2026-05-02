import api from '../api';
import type {Author, PageResponse} from '../types';

export const authorsRepository = {
    async getAll() {
        const response = await api.get<PageResponse<Author>>('/authors');
        return response.data.content;
    },

    async getById(id: string) {
        const response = await api.get<Author>(`/authors/${id}`);
        return response.data;
    },
};
