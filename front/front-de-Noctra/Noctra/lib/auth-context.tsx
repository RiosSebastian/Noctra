'use client';

import { createContext, useContext, useEffect, useState, ReactNode } from 'react';
import * as api from './api';

const TOKEN_KEY = 'noctra_token';

interface JwtPayload {
  sub: string; // email
  exp: number; // segundos unix
}

function decodeToken(token: string): JwtPayload | null {
  try {
    const payload = token.split('.')[1];
    return JSON.parse(atob(payload));
  } catch {
    return null;
  }
}

function isExpired(payload: JwtPayload): boolean {
  return payload.exp * 1000 < Date.now();
}

interface AuthContextValue {
  token: string | null;
  email: string | null;
  isLoading: boolean;
  login: (email: string, password: string) => Promise<void>;
  register: (username: string, email: string, password: string) => Promise<void>;
  logout: () => void;
}

const AuthContext = createContext<AuthContextValue | undefined>(undefined);

export function AuthProvider({ children }: { children: ReactNode }) {
  const [token, setToken] = useState<string | null>(null);
  const [email, setEmail] = useState<string | null>(null);
  const [isLoading, setIsLoading] = useState(true);

  // Al montar, recuperamos la sesión guardada (si sigue vigente)
  useEffect(() => {
    const stored = localStorage.getItem(TOKEN_KEY);
    if (stored) {
      const payload = decodeToken(stored);
      if (payload && !isExpired(payload)) {
        setToken(stored);
        setEmail(payload.sub);
      } else {
        localStorage.removeItem(TOKEN_KEY);
      }
    }
    setIsLoading(false);
  }, []);

  function applyToken(newToken: string) {
    localStorage.setItem(TOKEN_KEY, newToken);
    setToken(newToken);
    const payload = decodeToken(newToken);
    setEmail(payload?.sub ?? null);
  }

  async function login(emailInput: string, password: string) {
    const { token: newToken } = await api.login(emailInput, password);
    applyToken(newToken);
  }

  async function register(username: string, emailInput: string, password: string) {
    const { token: newToken } = await api.register(username, emailInput, password);
    applyToken(newToken);
  }

  function logout() {
    localStorage.removeItem(TOKEN_KEY);
    setToken(null);
    setEmail(null);
  }

  return (
    <AuthContext.Provider value={{ token, email, isLoading, login, register, logout }}>
      {children}
    </AuthContext.Provider>
  );
}

export function useAuth(): AuthContextValue {
  const ctx = useContext(AuthContext);
  if (!ctx) throw new Error('useAuth debe usarse dentro de <AuthProvider>');
  return ctx;
}
