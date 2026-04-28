import { useState, useEffect } from 'react';
import type {Author} from '../types';
import { libraryService } from '../services/libraryService';

export const useAuthors = () => {
    const [authors, setAuthors] = useState<Author[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const loadAuthors = async () => {
            try {
                const response = await libraryService.fetchAuthors();
                setAuthors(response.data.content);
            } catch (err) {
                setError('Грешка при вчитување на авторите.');
            } finally {
                setLoading(false);
            }
        };

        loadAuthors();
    }, []);

    return { authors, loading, error };
};