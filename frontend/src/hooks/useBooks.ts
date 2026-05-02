import {useEffect, useState} from "react";
import type {Book} from "../types";
import {booksRepository} from "../repositories/booksRepository";

export const useBooks = () => {
    const [books, setBooks] = useState<Book[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const loadBooks = async () => {
            try {
                setBooks(await booksRepository.getAll());
            } catch {
                setError('Error when loading books')
            } finally {
                setLoading(false);
            }
        };

        loadBooks();

    }, [])

    return {books, loading, error};
};

export const useBook = (id: string | undefined) => {
    const [book, setBook] = useState<Book | null>(null);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const loadBook = async () => {
            if (!id) {
                setError('Book id is missing.');
                setLoading(false);
                return;
            }

            try {
                setBook(await booksRepository.getById(id));
            } catch {
                setError('Error when loading book details')
            } finally {
                setLoading(false);
            }
        };

        loadBook();
    }, [id]);

    return {book, loading, error};
};
