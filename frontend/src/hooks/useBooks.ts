import {useEffect, useState} from "react";
import type {Book} from "../types";
import {libraryService} from "../services/libraryService.ts";

export const useBooks = () => {
    const [books, setBooks] = useState<Book[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const loadBooks = async () => {
            try {
                const response = await libraryService.fetchBooks();
                setBooks(response.data.content);
            } catch (error) {
                setError('Error when loading books')
            } finally {
                setLoading(false);
            }
        };

        loadBooks();

    }, [])

    return {books, loading, error};
};

