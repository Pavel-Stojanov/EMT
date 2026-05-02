import {useCallback, useEffect, useMemo, useState, type ReactNode} from 'react';
import {
    clearStoredToken,
    getStoredToken,
    setStoredToken,
    setUnauthorizedHandler,
} from '../api';
import {authRepository} from '../repositories/authRepository';
import type {LoginRequest, RegisterRequest} from '../types';
import {AuthContext} from './AuthContext';

interface AuthProviderProps {
    children: ReactNode;
}

export default function AuthProvider({children}: AuthProviderProps) {
    const [token, setToken] = useState<string | null>(() => getStoredToken());

    const storeToken = useCallback((nextToken: string) => {
        setStoredToken(nextToken);
        setToken(nextToken);
    }, []);

    const logout = useCallback(() => {
        clearStoredToken();
        setToken(null);
    }, []);

    useEffect(() => {
        setUnauthorizedHandler(logout);
        return () => setUnauthorizedHandler(null);
    }, [logout]);

    const login = useCallback(async (credentials: LoginRequest) => {
        const response = await authRepository.login(credentials);
        storeToken(response.token);
    }, [storeToken]);

    const register = useCallback(async (request: RegisterRequest) => {
        const response = await authRepository.register(request);
        storeToken(response.token);
    }, [storeToken]);

    const value = useMemo(() => ({
        token,
        isAuthenticated: Boolean(token),
        login,
        register,
        logout,
    }), [login, logout, register, token]);

    return (
        <AuthContext.Provider value={value}>
            {children}
        </AuthContext.Provider>
    );
}
