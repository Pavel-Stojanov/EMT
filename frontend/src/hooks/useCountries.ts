import { useState, useEffect } from 'react';
import type {Country} from '../types';
import { libraryService } from '../services/libraryService';

export const useCountries = () => {
    const [countries, setCountries] = useState<Country[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<string | null>(null);

    useEffect(() => {
        const loadCountries = async () => {
            try {
                const response = await libraryService.fetchCountries();
                // Бекендот враќа обична листа за земји
                setCountries(response.data);
            } catch (err) {
                setError('Грешка при вчитување на земјите.');
            } finally {
                setLoading(false);
            }
        };

        loadCountries();
    }, []);

    return { countries, loading, error };
};