'use client';

import { useState, FormEvent } from 'react';
import { useRouter } from 'next/navigation';
import Link from 'next/link';
import { useAuth } from '@/lib/auth-context';

export default function LoginPage() {
  const router = useRouter();
  const { login } = useAuth();

  const [email, setEmail] = useState('');
  const [password, setPassword] = useState('');
  const [error, setError] = useState<string | null>(null);
  const [isSubmitting, setIsSubmitting] = useState(false);

  async function handleSubmit(e: FormEvent) {
    e.preventDefault();
    setError(null);
    setIsSubmitting(true);

    try {
      await login(email, password);
      router.push('/');
    } catch (err) {
      setError(err instanceof Error ? err.message : 'No se pudo iniciar sesión');
    } finally {
      setIsSubmitting(false);
    }
  }

  return (
    <div className="min-h-screen flex items-center justify-center bg-noctra-aurora px-6">
      <div className="w-full max-w-sm">
        <Link
          href="/"
          className="font-display block text-center text-2xl font-extrabold tracking-tight text-violet-glow mb-8"
        >
          NOCTRA
        </Link>

        <form
          onSubmit={handleSubmit}
          className="rounded-lg border border-noctra-border bg-noctra-surface p-8 shadow-glow"
        >
          <h1 className="font-display text-xl font-bold mb-6">Iniciar sesión</h1>

          {error && (
            <div className="mb-4 rounded-md border border-red-500/30 bg-red-500/10 px-4 py-2 text-sm text-red-300">
              {error}
            </div>
          )}

          <label className="block text-sm font-medium text-ink-400 mb-1" htmlFor="email">
            Email
          </label>
          <input
            id="email"
            type="email"
            required
            autoComplete="email"
            value={email}
            onChange={(e) => setEmail(e.target.value)}
            className="mb-4 w-full rounded-md border border-noctra-border bg-noctra-surface2 px-3 py-2 text-sm text-ink-50 outline-none focus:border-violet"
            placeholder="vos@ejemplo.com"
          />

          <label className="block text-sm font-medium text-ink-400 mb-1" htmlFor="password">
            Contraseña
          </label>
          <input
            id="password"
            type="password"
            required
            autoComplete="current-password"
            value={password}
            onChange={(e) => setPassword(e.target.value)}
            className="mb-6 w-full rounded-md border border-noctra-border bg-noctra-surface2 px-3 py-2 text-sm text-ink-50 outline-none focus:border-violet"
            placeholder="••••••••"
          />

          <button
            type="submit"
            disabled={isSubmitting}
            className="w-full rounded-full bg-violet px-5 py-2.5 text-sm font-semibold text-ink-50 shadow-glow transition-colors hover:bg-violet-deep disabled:opacity-60"
          >
            {isSubmitting ? 'Ingresando...' : 'Iniciar sesión'}
          </button>

          <p className="mt-6 text-center text-sm text-ink-400">
            ¿No tenés cuenta?{' '}
            <Link href="/registro" className="text-violet-glow hover:underline">
              Registrate
            </Link>
          </p>
        </form>
      </div>
    </div>
  );
}