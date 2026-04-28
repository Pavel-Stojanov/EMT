import {useEffect, useState} from "react";
import type {CategoryStatistics} from "../types";
import {libraryService} from "../services/libraryService.ts";

export const useCategoryStatistics = () => {
    const [statistics, setStatistics] = useState<CategoryStatistics[]>([]);
    const [loading, setLoading] = useState<boolean>(true);
    const [error, setError] = useState<String | null>(null);

    useEffect(() => {
        const fetchStatistics = async () => {
            try {
                const response = await libraryService.fetchCategoryStatistics();
                setStatistics(response.data);
            } catch (error) {
                setError('Failed to load Statistics');
            } finally {
                setLoading(false);
            }
        };
        fetchStatistics();
    }, []);
    return {statistics, loading, error};
}