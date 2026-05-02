import { useState, useEffect } from 'react';
import type {Country} from '../types';
import { countriesRepository } from '../repositories/countriesRepository';

export const useCountries = () => {
    const [countries, setCountries] = useState<Country[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const loadCountries = async () => {
            try {
                setCountries(await countriesRepository.getAll());
            } catch {
                setError('Грешка при вчитување на земјите.');
            } finally {
                setLoading(false);
            }
        };

        loadCountries();
    }, []);

    return { countries, loading, error };
};

export const useCountry = (id: string | undefined) => {
    const [country, setCountry] = useState<Country | null>(null);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const loadCountry = async () => {
            if (!id) {
                setError('Country id is missing.');
                setLoading(false);
                return;
            }

            try {
                setCountry(await countriesRepository.getById(id));
            } catch {
                setError('Грешка при вчитување на деталите за земјата.');
            } finally {
                setLoading(false);
            }
        };

        loadCountry();
    }, [id]);

    return { country, loading, error };
};
