import type {Author, Book, Country, PageResponse} from '../types';
import api from "../api.ts";

export const libraryService = {
    login: (username: string, password: string) => {
        return api.post<{ token: string }>('/auth/login', {username, password});
    },
    register: (data: any) => {
        return api.post<{ token: string }>('/auth/register', data)
    },
    fetchBooks: () => {
        return api.get<PageResponse<Book>>('/books');
    },
    fetchAuthors: () => {
        return api.get<PageResponse<Author>>('/authors');
    },
    fetchCountries: () => {
        return api.get<Country[]>('/countries');
    }
};