import api from '../api';
import type {Book, PageResponse} from '../types';

export const booksRepository = {
    async getAll() {
        const response = await api.get<PageResponse<Book>>('/books');
        return response.data.content;
    },

    async getById(id: string) {
        const response = await api.get<Book>(`/books/${id}`);
        return response.data;
    },
};
