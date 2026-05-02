import { useState, useEffect } from 'react';
import type {Author} from '../types';
import { authorsRepository } from '../repositories/authorsRepository';

export const useAuthors = () => {
    const [authors, setAuthors] = useState<Author[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const loadAuthors = async () => {
            try {
                setAuthors(await authorsRepository.getAll());
            } catch {
                setError('Грешка при вчитување на авторите.');
            } finally {
                setLoading(false);
            }
        };

        loadAuthors();
    }, []);

    return { authors, loading, error };
};

export const useAuthor = (id: string | undefined) => {
    const [author, setAuthor] = useState<Author | null>(null);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const loadAuthor = async () => {
            if (!id) {
                setError('Author id is missing.');
                setLoading(false);
                return;
            }

            try {
                setAuthor(await authorsRepository.getById(id));
            } catch {
                setError('Грешка при вчитување на деталите за авторот.');
            } finally {
                setLoading(false);
            }
        };

        loadAuthor();
    }, [id]);

    return { author, loading, error };
};
